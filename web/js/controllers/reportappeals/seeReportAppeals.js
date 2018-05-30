/**
 * Created by 赵传志 on 2018/3/25.
 */

app.controller('seeMyReportAppealController', ['$scope', '$http', '$modalInstance', 'id', function ($scope, $http, $modalInstance, id) {



    // console.log(id);
    $http.get("report_appeals/"+id).success(function (data) {
        $scope.reportAppeal = data.data;
        console.log($scope.reportAppeal)
    }).error(function (err) {
        alert(err.error);
    });

    /**
     * 关闭新增窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };
}]);