/**
 * Created by 赵传志 on 2018/3/25.
 */

app.controller('updateReportAppealController', ['$scope', '$http', '$modalInstance', 'reportAppealDto', function ($scope, $http, $modalInstance, reportAppealDto) {

    $scope.reportAppealDto = reportAppealDto;


    // 提交
    $scope.submit = function () {
        $http.put('report_appeals', $scope.reportAppealDto).success(function (data) {
            if (data.status == "SUCCESS")
                $scope.close('SUCCESS');
            else{
                $scope.pop('error', '', data.error);
            }

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
}]);
