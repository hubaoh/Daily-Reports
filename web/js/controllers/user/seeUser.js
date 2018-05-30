/**
 * Created by 赵传志 on 2018/2/5.
 */
app.controller('seeUserController', ['$scope', '$http', '$modalInstance', 'userDto',  function ($scope, $http, $modalInstance, userDto) {

    $scope.userDto = userDto;
    /**
     * 关闭新增窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };
}]);


