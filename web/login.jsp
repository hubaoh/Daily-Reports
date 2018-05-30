<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>工作日报管理系统</title>

    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/libs/jquery/jquery-1.11.0.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/libs/jquery/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/js/libs/jquery/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/js/libs/jquery/bootstrap/css/bootstrap-theme.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/login.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/js/libs/angular/angularjs-toaster/toaster.css">

    <link rel="shortcut icon" href="/favicon.ico" type="img/x-ico" />
    <link rel="bookmark" href="/favicon.ico"/>


    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/libs/angular/angular/angular.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/libs/angular/angular-animate/angular-animate.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/libs/angular/angularjs-toaster/toaster.js"></script>
</head>
<body ng-app="app" ng-controller="loginController">

<div id="formBackground">

    <div style="margin-top: 5%;"><img src="img/logo2.png" width="60px" height="60px"></div>
    <div class="title">工作日报管理系统</div>
    <div class="form">
        <div style="text-align: center;">
            <toaster-container
                    toaster-options="{'position-class': 'toast-top-center', 'close-button':true}"></toaster-container>
        </div>
        <form>

            <div class="input-group input-group-lg">
					<span class="input-group-addon" id="login-addon1"><i
                            class="glyphicon glyphicon-user"></i></span> <input type="text" id="userName"
                                                                                name="userName" placeholder="请输入用户名"
                                                                                aria-describedby="login-addon1"
                                                                                class="form-control" ng-model="userName">
            </div>

            <div class="input-group input-group-lg">
					<span class="input-group-addon" id="login-addon2"><i
                            class="glyphicon glyphicon-lock"></i></span> <input type="password"
                                                                                id="password" name="password"
                                                                                placeholder="请输入密码"
                                                                                aria-describedby="login-addon2"
                                                                                class="form-control" ng-model="password">
            </div>
            <br> <input type="button" value="Login" ng-click="submit()"
                        class="btn btn-primary col-lg-12">
        </form>
        <div class="findpassword">
            <a href="${pageContext.request.contextPath }/findPassword.html">忘记密码?</a>
        </div>
    </div>


</div>
<script type="text/javascript">
    var app = angular.module('app',['toaster','ngAnimate']);
    app.controller('loginController', ['$scope','$http','toaster',function($scope, $http,toaster){

        $scope.userName = "";
        $scope.password = "";

        $scope.pop = function (type, title, text) {
            toaster.pop(type, '', text);
        };

        $scope.submit = function(){
            if ($scope.userName == "" || $scope.password == ""){
                if ($scope.userName == ""){
                    $scope.pop('warning', '警告', '用户名不能为空');
                }
                if($scope.password == ""){
                    $scope.pop('warning', '警告', '密码不能为空');
                }
            }else {
                console.log($scope.userName);
                $http.get("login/currentUser?userName="+$scope.userName+"&password="+$scope.password).success(function (data) {
                    console.log(data.data);
                    if (data.status == "SUCCESS"){
                        window.location="login/system";
                    }else {
                        $scope.pop('error', '错误', '用户名或密码错误');
                    }

                }).error(function(err){
                    $scope.pop('error', '错误', '用户名或密码错误');
                });
            }
        };



    }]);

</script>
</body>
</html>