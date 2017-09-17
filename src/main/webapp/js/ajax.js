
/**
 * @fileoverview ajax请求公用组件
 * @author Limo
 * @date 2015/08/07
 * Native package ajax method, make it like the ajax of zepto Lib.
 */
var querystring = require('querystring');
function ajax( opts ) {
    // 创建ajax对象
    var xhr = null,
        abortTimeout = null,
        empty =function(){},
        ajax_url = "",
        opts = {
            type : ( opts.type && opts.type.toUpperCase() ) || 'GET',
            url : opts.url || "",
            data : opts.data || "", //query
            dataType : opts.dataType || 'json',
            success : opts.success || empty,
            error : opts.error || empty,
            timeout : opts.timeout || 30000 //默认超时时间：30S ，与产品交互保持一致
        };

    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    }

    opts.data = querystring.stringify( opts.data );

    if (opts.type == 'GET') {
        if(opts.url.indexOf("?")>-1){
            if( opts.data =="" ){
                ajax_url = opts.url;
            } else {
                ajax_url = opts.url + '&' + opts.data;
            }
        } else {
            ajax_url = opts.url + '?' + opts.data;
        }
        xhr.open('GET', ajax_url , true);
        xhr.send();

    } else if (opts.type == 'POST') {
        xhr.open('POST', opts.url, true);
        // 如果需要像 html 表单那样 POST 数据，请使用 setRequestHeader() 来添加 http 头。
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send( opts.data );
    }

    // 处理返回数据
    xhr.onreadystatechange = function () {
        /*
         ** 每当readyState改变时，就会触发onreadystatechange事件
         ** readyState属性存储有XMLHttpRequest的状态信息
         ** 0 ：请求未初始化
         ** 1 ：服务器连接已建立
         ** 2 ：请求已接受
         ** 3 : 请求处理中
         ** 4 ：请求已完成，且相应就绪
         */
        if (xhr.readyState == 4) {
            var res,
                error;
            xhr.onreadystatechange = empty;
            clearTimeout( abortTimeout );
            // console.log( "xhr.status: " , xhr.status );
            /*
             ** Http状态码
             ** 1xx ：信息展示
             ** 2xx ：成功
             ** 3xx ：重定向
             ** 4xx : 客户端错误
             ** 5xx ：服务器端错误
             */
            // var protocol = /^([\w-]+:)\/\//.test(opts.url) ? RegExp.$1 : window.location.protocol;
            // if ( (xhr.status >= 200 && xhr.status < 300) || xhr.status == 304 || (xhr.status == 0 && protocol == 'file:') ) {
            if ( (xhr.status >= 200 && xhr.status < 300) || xhr.status == 304 ) {
                res = xhr.responseText; // xhr.responseText 和 xhr.response 结果相同
                try {
                    // console.info( "snsnav request success" );
                    if( opts.type == 'GET' ){
                        if( opts.dataType == "json" ){
                            res = JSON.parse( xhr.responseText );
                        } else if ( opts.dataType == 'script' ) {
                            // http://perfectionkills.com/global-eval-what-are-the-options/
                            (1,eval)(res);
                        } else if ( opts.dataType == 'xml' ) {
                            res = xhr.responseXML;
                        }
                    }
                    // else if( opts.type == 'POST' ){
                    // }
                } catch (e) {
                    error = e;
                }
                if( error ){
                    opts.error( error, 'parsererror' , xhr );
                } else {
                    opts.success( res );
                }
            } else {
                // opts.error( xhr.statusText || 'unknown' , xhr.status ? 'error' : 'abort' , xhr );
                // xhr.status=0时，相关介绍：http://www.w3.org/TR/XMLHttpRequest/
                // The status attribute must return the result of running these steps:
                // 1、If the state is UNSENT or OPENED, return 0.
                // 2、If the error flag is set, return 0.
                // 3、Return the HTTP status code.
                // var xhr_status = xhr.status || 'unknown';
                opts.error( xhr.statusText || 'unknown' , 'status:'+xhr.status , xhr );
            }
            // console.log( "xhr.statusText: " , xhr.statusText );
        }
    };

    // function ajaxError( error, type, xhr ){ }
    // opts.error( error, 'parsererror',xhr );
    // type: "timeout", "error", "abort", "parsererror"

    if (opts.timeout > 0){ //设置超时时间
        abortTimeout = setTimeout(function(){
            xhr.onreadystatechange = empty;
            //取消当前响应，关闭连接并且结束任何未决的网络活动
            xhr.abort();

            //请求时间 超过前端设置的超时时间
            opts.error('Request.timeout','timeout',xhr);
        }, opts.timeout);
    }
    return xhr;
}
module.exports = ajax;
/*
 //ajax调用方法：
 var ajax = require('../../common/util/ajax.js');
 ajax({
 url: url,
 dataType: 'json',
 data : {
 'param1' : '111',
 'param2' : '222'
 },
 success: function (result) {
 console.log( "result:" , typeof result );
 //success callback
 },
 timeout : 30000, //超时时间：30s
 error: function ( error, type, xhr ) {
 console.error( "error:",error, "type:",type, "xhr:",xhr );
 //error callback
 }
 });
 */