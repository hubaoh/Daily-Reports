/**
 * Created by 赵传志 on 2018/3/10.
 */


app.controller('createReportController', ['$scope', '$http', '$modalInstance', 'toaster', function ($scope, $http, $modalInstance, toaster) {

    /**
     * 将前端收集到的数据插入数据库中
     */
    
    $scope.create = function () {
        $http.post('reports', $scope.report).success(function (data) {
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

    /**
     * 时间
     * @param $event
     */
    $scope.createTimeTool = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.createTimeOpened = true;
    };
}]);



