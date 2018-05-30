/**
 * Created by 赵传志 on 2018/3/13.
 */


app.controller('seeAllReportController', ['$scope', '$http', '$modalInstance', 'report', function ($scope, $http, $modalInstance, report) {

    $scope.report = report;
    /**
     * 关闭新增窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };
}]);


