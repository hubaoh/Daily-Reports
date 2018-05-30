/**
 * Created by 赵传志 on 2018/3/13.
 */

app.controller('updateReportCheckedController', ['$scope', '$http',  'toaster','id','$modalInstance', function ($scope, $http, toaster, id, $modalInstance) {

    //提示信息
    $scope.toaster = {
        type: 'success',
        title: 'Title',
        text: 'Message'
    };

    $scope.pop = function(type,title,text){
        toaster.pop(type,'',text);
    };

    $http.get("report_checkeds/"+id).success(function (data) {
        $scope.reportChecked = data.data;
    });

    /**
     * 将前端收集到的数据插入数据中
     */
    $scope.create = function () {
        $http.put('report_checkeds', $scope.reportChecked).success(function (data) {
            if (data.status == "SUCCESS")
                $scope.close('SUCCESS');
            else{
                $scope.pop('error', '', data.error);
            }

        }).error(function (err) {
            alert(err.error);
        });
    };
    
    $scope.info = function(){
        if ($scope.reportChecked.report_workScore > 80){
            $scope.pop('warning','','今日工作记录分数不能超过80分！');
        }

        if ($scope.reportChecked.report_planScore > 20){
            $scope.pop('warning','','明日计划分数不能超过20分！');
        }
    };


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



