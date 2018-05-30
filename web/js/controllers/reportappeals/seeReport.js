/**
 * Created by 赵传志 on 2018/3/25.
 */


app.controller('seeReportController', ['$scope', '$http', '$modalInstance', 'reportAppealDto', function ($scope, $http, $modalInstance, reportAppealDto) {

    $scope.reportAppealDto = reportAppealDto;
    /**
     * 关闭新增窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };
}]);


