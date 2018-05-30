/**
 * Created by 赵传志 on 2018/4/25.
 */

app.controller('fileUploadController', ['$scope', '$http', 'toaster','$modalInstance', function ($scope, $http, toaster, $modalInstance) {

    //提示信息
    $scope.toaster = {
        type: 'success',
        title: 'Title',
        text: 'Message'
    };

    $scope.pop = function(type,title,text){
        toaster.pop(type,'',text);
    };

    // 文件上传
    $scope.save = function () {
        var file = new FormData();
        var fileUpload = document.getElementById("fileUpload").files[0];
        file.append('file', fileUpload);
        file.append('desc',$scope.desc);
        file.append('title',$scope.title);
        $http.post('file/upload',file,{
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function (data) {
            if (data.status == "SUCCESS")
                $scope.close('SUCCESS');
        }).error(function (data) {
            alert('文件上传失败');
        })

        // var form = new FormData($('#fileUpload')[0]);
        // $.ajax({
        //     type: 'post',
        //     url: "file/upload",
        //     data: form,
        //     cache: false,
        //     processData: false,
        //     contentType: false,
        // }).success(function (data) {
        //     alert(data);
        // }).error(function () {
        //     alert("上传失败");
        // });
    };

    /**
     * 将前端收集到的数据插入数据库中
     */
    // $scope.save = function () {
    //     $http.post('file/upload', $scope.uploadFile).success(function (data) {
    //         if (data.status == "SUCCESS")
    //             $scope.close('SUCCESS');
    //         else
    //             $scope.pop('error', '', data.error);
    //     }).error(function (err) {
    //         alert(err.error);
    //     });
    // }


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




