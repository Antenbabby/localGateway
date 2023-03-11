const CONTEX='/gateway';

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

function getFileName() {
    let ret = getQueryString('file');
    if (!!ret) {
        return ret;
    } else {
        return 'index.md';
    }
}