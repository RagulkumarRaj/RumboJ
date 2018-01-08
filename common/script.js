var webPage = require('webpage');
var page = webPage.create();

var fs = require('fs');

page.customHeaders = {
	"plugins": {
		"length": "2",
		"Shockwave Flash": {
			"name": "Shockwave Flash",
			"description": "Shockwave Flash 11.6 r602"
		}
	},
	"mimeTypes": {
		"length": 2,
		"application/x-shockwave-flash": {
			"description": "Shockwave Flash",
			"suffixes": "swf",
			"type": "application/x-shockwave-flash",
			"enabledPlugin": {
				"description": "Shockwave Flash 11.6 r602"
			}
		}
	},
	"Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
	"appCodeName": "Mozilla",
	"appName": "Netscape",
	"appVersion": "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36",
	"cookieEnabled": true,
	"language": "en",
	"onLine": true,
	"platform": "CentOS 5.7",
	"product": "Gecko",
	"productSub": "20030107",
	"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22"
};

page.onInitialized = function () {
	page.customHeaders = {};
};
	
    page.open("https://www.flipkart.com/search?q=google%20mobiles&otracker=start&as-show=on&as=off", function() {
      //console.log("Inside page open");
	  
      page.evaluate(function() {
      });
    });

    page.onLoadFinished = function() {
        //console.log("page load finished");
        //page.render('export.jpg');
        //fs.write('C:\\PhantomJS\\BrowserDetails.html', page.content, 'w');
		console.log(page.content);
        phantom.exit();
    };
	
	page.onResourceRequested = function(requestData, networkRequest) {
       //console.log('Request (#' + requestData.id + '): ' + JSON.stringify(requestData));
    };
	
	page.onError = function(msg, trace) {
		//console.log("inside error log");
    var msgStack = ['ERROR: ' + msg];
    if (trace && trace.length) {
        msgStack.push('TRACE:');
        trace.forEach(function(t) {
            msgStack.push(' -> ' + t.file + ': ' + t.line + (t.function ? ' (in function "' + t.function + '")' : ''));
        });
    }
    //console.error(msgStack.join('\n'));
};
