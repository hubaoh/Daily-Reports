/**
 * Created by 赵传志 on 2018/3/5.
 */

app.controller('createDeptController', ['$scope', '$http', '$modalInstance', 'toaster', function ($scope, $http, $modalInstance, toaster) {

    //提示信息
    $scope.toaster = {
        type: 'success',
        title: 'Title',
        text: 'Message'
    };

    $scope.pop = function(type,title,text){
        toaster.pop(type,'',text);
    };
    
    /**
     * 将前端收集到的数据插入数据中
     */
    $scope.create = function () {
        $http.post('depts', $scope.dept).success(function (data) {
            if (data.status == "SUCCESS")
                $scope.close('SUCCESS');
            else
                $scope.pop('error', '', data.error);
        }).error(function (err) {
            alert(err.error);
        });
    }


    /**
     * 关闭新增窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };

    $scope.timeTool = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.foundTimeOpened = true;
    };

}]);




