/**
 * Created by 赵传志 on 2018/3/25.
 */

app.controller('seeReportCheckedController', ['$scope', '$http',  'toaster','reportAppealDto','$modalInstance', function ($scope, $http, toaster, reportAppealDto, $modalInstance) {

    //提示信息
    $scope.toaster = {
        type: 'success',
        title: 'Title',
        text: 'Message'
    };

    $scope.pop = function(type,title,text){
        toaster.pop(type,'',text);
    };

    // $http.get("report_checkeds/"+id).success(function (data) {
    //     $scope.reportChecked = data.data;
    // });

    $scope.reportAppealDto = reportAppealDto;


    /**
     * 关闭新增窗口
     * @param status
     */
    $scope.close = function (status) {
        $modalInstance.close(status);
    };

    $scope.timeTool = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.foundTimeOpened = true;
    };

}]);



