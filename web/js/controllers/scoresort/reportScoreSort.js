/**
 * Created by 赵传志 on 2018/4/12.
 */

app.controller('carNumberSuperviseController', [ '$scope', '$http','$filter', function ($scope, $http, $filter) {

    $scope.report_time = $filter('date')(new Date(),'yyyy-MM-dd');
    $scope.getPagedDataAsync = function () {
        var url = 'report_checkeds/sort';
        if ($scope.report_time != "" && $scope.report_time != null ){
            url += '?report_time=' + $filter('date')($scope.report_time,'yyyy-MM-dd');
        }
        $http.get(url).success(function (data) {
            $scope.reportChecked = data.data;
        });

    };

    $scope.getPagedDataAsync();
    // 改变时间的同时改变报表的数据
    $scope.$watch('report_time', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPagedDataAsync();
        }
    }, true);


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




