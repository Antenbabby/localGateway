const webPath = 'https://raw.githubusercontent.com/Antenbabby/localGateway/cloudflare/src/main/resources/static'; //web页面地址，可以修改成自己的仓库来自定义前端页面
const setCache = (key, data) => DOCDATA.put(key, data)
const getCache =( key,format) => DOCDATA.get(key, format)

// export default {
//     async fetch(request, _env) {
//         return await handleRequest(request);
//     }
// }

addEventListener('fetch', event => {
    event.respondWith(handleRequest(event.request))
})

/**
 * Respond to the request
 * @param {Request} request
 */
async function handleRequest(request) {
    let url = new URL(request.url);
    let path = url.pathname;

    if (path.startsWith("/static/")||path === "/favicon.ico") { //web请求
        let a = `${webPath}${path}`;
        return await goWeb(a);
    }
    if (path.startsWith("/gateway/")) { //后台请求
        return await goApi(path,request);
    }
    return getRedirect('/static/index.html');
}


async function goWeb(path) {
    let res = await fetch(path);
    let mimeType='text/plain;charset=UTF-8';
    if (path.endsWith(".html")) {
        mimeType = "text/html; charset=utf-8";
    } else if (path.endsWith(".js")) {
        mimeType = "application/x-javascript; charset=utf-8";
    } else if (path.endsWith(".css")) {
        mimeType = "text/css; charset=utf-8";
    } else if (path.endsWith(".png")) {
        mimeType = "image/png";
    } else if (path.endsWith(".ico")) {
        mimeType = "image/png";
    }
    return new Response(res.body, {
        status: 200,
        statusText: 'ok',
        headers: {
            "content-type": mimeType,
            "cache-control":"max-age=14400"

        }
    });
}

async function goApi(path,request) {
    // 用正则表达式匹配出getFile和index.md
    let regex = /\/gateway\/api\/(\w+)\/(\w+\.\w+)/;
    // 用match方法得到一个数组，第一个元素是整个匹配，第二个元素是第一个分组，第三个元素是第二个分组
    let matches = path.match(regex);
    // 如果匹配成功，把结果赋值给变量method和param
    if (matches) {

        let method = matches[1];
        let pathVal = matches[2];
        if(method==='getFile') {
            // 如果method是getFile，就返回文件内容
            let fileText =await  getCache(pathVal, {type: "text"});
            return retText(fileText);
        }else if(method==='updateFile'){
            let bodyParam=await request.json();
            // updateFile方法用于更新文件内容
            await setCache(pathVal,bodyParam.content);
            return retText("更新成功");
        }
    }
}

//返回文本内容
function retText(text) {
    return new Response(text, {
        status: 200,
        statusText: 'ok',
        headers: {
            "content-type": 'text/plain;charset=UTF-8',
            "cache-control": "no-cache"
        }
    });
}

//返回重定向
function getRedirect(url) {
    return new Response("正在重定向到" + url, {
        status: 302,
        statusText: 'redirect',
        headers: {
            "content-type": "text/html",
            "location": url
        }
    })
}

