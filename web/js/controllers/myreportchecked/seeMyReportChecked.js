/**
 * Created by 赵传志 on 2018/3/14.
 */


app.controller('seeMyReportCheckedController', ['$scope', '$http',  'toaster','reportCheckedDto','$modalInstance', function ($scope, $http, toaster, reportCheckedDto, $modalInstance) {

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
    
    $scope.reportCheckedDto = reportCheckedDto;
    

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



