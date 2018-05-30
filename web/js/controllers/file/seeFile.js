/**
 * Created by 赵传志 on 2018/4/26.
 */

app.controller('seeFileController', ['$scope', '$http', '$modalInstance', 'file', function ($scope, $http, $modalInstance, file) {

    $scope.file = file;
    /**
     * 关闭新增窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };
}]);


