/**
 * Created by 赵传志 on 2018/3/14.
 */

app.controller('createReportAppealController', ['$scope', '$http', '$modalInstance', 'toaster','reportCheckedDto', function ($scope, $http, $modalInstance, toaster,reportCheckedDto) {
    
    $scope.reportCheckedDto = reportCheckedDto;
    /**
     * 将前端收集到的数据插入数据库中
     */
    $scope.appeal = function () {
        $http.post('report_appeals', $scope.reportCheckedDto).success(function (data) {
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



