/**
 * Created by 赵传志 on 2018/2/5.
 */
app.controller('updateUserController', ['$scope', '$http', '$modalInstance', 'userId', function ($scope, $http, $modalInstance, userId) {
    function init() {

        $http.get("depts/all").success(function (data) {
            $scope.depts = data.data;
        });
        $http.get("roles/all").success(function (data) {
            $scope.roles = data.data;
        });

        $http.get("users/" + userId).success(function (data) {
            $scope.userDto = data.data;
            $scope.role = data.data.role;
            $scope.dept = data.data.dept;
        })

        
    }

    init();
    /**
     * 保存客户信息
     */
    $scope.update = function () {
        $http.put('users/' + userId, $scope.userDto).success(function (data) {
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

