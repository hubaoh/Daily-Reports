/**
 * 
 * Created by 赵传志 on 2018/4/16.
 */

'use strict';
app.controller('personalInfoController', ['$scope', '$http', 'toaster', '$modal', function ($scope, $http, toaster, $modal) {
    
    $scope.user="";
    //提示信息
    $scope.toaster = {
        type: 'info',
        title: 'Title',
        text: 'Message'
    };

    $scope.pop = function (type, title, text) {
        toaster.pop(type, '', text);
    };

    $scope.getPagedDataAsync = function(){
        $http.get("users/current").success(function (data) {
            var user = data.data;
            if (user.roleValue == "01"){
                user.roleValue = "系统管理员"
            }
            if (user.roleValue == "02"){
                user.roleValue = "部门管理员"
            }
            if (user.roleValue == "03"){
                user.roleValue = "普通用户"
            }

            $scope.user = user;
            console.log($scope.user);
        });
    };

    $scope.getPagedDataAsync();

    // 完善信息
    $scope.updateInfo = function () {
        var rtn = $modal.open({
            templateUrl: 'tpl/personalinf/update_info.html',
            controller: 'updateInfoController',
            resolve: {
                user: function () {
                    return $scope.user;
                }
            }
        });

        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '个人信息修改成功');
                $scope.getPagedDataAsync();
            }
        }, function () {
        });
    };

    // 修改密码
    $scope.updatePassWord = function () {
        var rtn = $modal.open({
            templateUrl: 'tpl/personalinf/update_password.html',
            controller: 'updatePassWordController',
            resolve: {
                id: function () {
                    return $scope.user.id;
                }
            }
        });

        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '密码修改成功');
                // $scope.getPagedDataAsync();
            }
        }, function () {
        });
    };

    
    /**
     * 时间
     * @param $event
     */
    $scope.createTimeTool = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.createTimeOpened = true;
    };

}])
;
