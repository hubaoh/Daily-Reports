/**
 * Created by 赵传志 on 2018/3/5.
 */
app.controller('updateDeptController', ['$scope', '$http', '$modalInstance', 'id', function ($scope, $http, $modalInstance, id) {
    function init() {
        $http.get("depts/" + id).success(function (data) {
            $scope.dept = data.data;
        })
    }

    init();
    /**
     * 保存客户信息
     */
    $scope.update = function () {
        $http.put('depts/' +  id,$scope.dept).success(function (data) {
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

