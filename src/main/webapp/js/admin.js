/**
 * /home/william/workspace/doume/src/main/webapp/WEB-INF/jsp/admin/admin.jsp
 */
(function($) {
    $.setObj = function(k, v) {
	if (v) {
	    var jsonstring = JSON.stringify(v);
	    sessionStorage.setItem(k, jsonstring);
	} else {
	    sessionStorage.removeItem(k);
	}
    }
    $.getObj = function(k) {
	var jsonstring = sessionStorage.getItem(k);
	return JSON.parse(jsonstring);
    }
})(jQuery);

var urlHOME = "http://localhost:8080/doume/admin/";
$(document).ready(function() {
    init();
    //$("form#formAdminLogin").submit(actionFormLogin);
    //$("form#formPutUser").submit(actionFormPutUser);
});

function init() {
    var usr = $.getObj("usr");
    if (usr == null) {
	window.setTimeout(function() {
	    $.mobile.changePage("#login");
	}, 500);
    }
}

function actionFormLogin() {
    var urlparam = urlHOME + "login.json?" + $(this).serialize();
    jQuery.post(urlparam, function(data) {
	if (data.ObjectModel != "ERROR") {
	    $.setObj("usr", data.ObjectModel);
	    $.mobile.changePage("#admin-home");
	} else {
	    alert("Error!");
	}
    });
    return false;
}

function actionFormPutUser() {
    var urlparam = urlHOME + "putUser.json?" + $(this).serialize();
    jQuery.post(urlparam, function(data) {
	console.log("putUser:" + JSON.stringify(data.ObjectModel));
    });
    return false;
}

function actionFormGoods() {
    console.log("actionFormGoods:");
    $.post(urlHOME + "putGoods.json", $(this).serialize(), function(data) {
	console.log("callBack:" + data);
    });
    return false;
}
