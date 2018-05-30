/**
 * Created by 赵传志 on 2018/4/26.
 */

app.controller('updatePassWordController', ['$scope', '$http', '$modalInstance', 'id', function ($scope, $http, $modalInstance, id) {

    /**
     * 保存客户信息
     */
    $scope.submit = function () {
        $http.put('users/password/'+id +'?password='+$scope.password).success(function (data) {
            if(data.status == 'SUCCESS'){
                $scope.close('SUCCESS');
            }
        })
    }


    $scope.timeTool = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.factoryDateOpened = true;
    };

    /**
     * 关闭修改窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };
}]);

