/**
 * Created by 赵传志 on 2018/4/25.
 */

'use strict';
app.controller('fileController', ['$scope', '$http', '$modal', 'toaster','$filter',function ($scope, $http, $modal, toaster, $filter) {
    $scope.uploadTime = $filter('date')(new Date(), 'yyyy-MM-dd');
    $scope.uploader = "";

    //提示信息
    $scope.toaster = {
        type: 'info',
        title: 'Title',
        text: 'Message'
    };

    $scope.pop = function (type, title, text) {
        toaster.pop(type, '', text);
    };

    // ngGrid初始化数据
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    };

    $scope.pagingOptions = {
        pageSizes: [10, 15, 20],
        pageSize: '10',
        currentPage: 1
    };

    $scope.gridOptions = {
        data: 'files',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'title', displayName: '主题', width: '200px'},
            {field: 'originalName', displayName: '名称', width: '400px'},
            {field: 'uploader', displayName: '上传者', width: '150px'},
            {
                field: 'remove',
                displayName: '操作',
                width: "300px",
                cellTemplate:
                '<button class="btn btn-primary btn-sm m-t-xs m-l-xs" title="详情" style="margin-top: 2px" ng-click="seeRowIndex(row.entity)">详情</button>' +
                    '<button class="btn btn-success  btn-sm m-t-xs m-l-xs" title="下载" style="margin-top: 2px; margin-left: 10px" ng-click="download(row.entity)" >下载</button>'
            }
        ]
    };

    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'file?page=' + page + '&pageSize=' + pageSize+ '&uploadTime=' + $filter('date')($scope.uploadTime, 'yyyy-MM-dd');
        if ($scope.uploader != "") {
            url += '&uploader=' + $scope.uploader;
        }

        $http.get(url).success(function (data) {
            $scope.files = data.data.list;
            $scope.totalServerItems = data.data.totalElements;
        });
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, "");

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal || newVal.currentPage !== oldVal.currentPage || newVal.pageSize !== oldVal.pageSize) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    // 改变时间的同时改变报表的数据
    $scope.$watch('uploadTime', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.search = function () {
        $scope.pagingOptions.currentPage = 1;
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, '');
    };

// 文件上传
    $scope.upload = function () {
        var rtn = $modal.open({
            templateUrl: 'tpl/file/upload_file.html',
            controller: 'fileUploadController',
            resolve: {}
        });

        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '文件上传成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }else if(status == 'ERROR') {
                $scope.pop('error', '', '文件上传失败');
            }
        }, function () {
        });
    };

    // 文件下载
    $scope.download = function(entity){
        console.log(entity.uuIdName);
        var filename = entity.uuIdName;
        window.location.href = "file/download?filename=" + filename;
    }
    
    
    /**
     * 查看文件详情
     * @param entity
     */
    $scope.seeRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/file/see_file.html',
            controller: 'seeFileController',
            resolve: {
                file: function () {
                    return entity
                }
            }
        });
        rtn.result.then(function (status) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }, function () {
        });
    };

    /**
     * 时间
     * @param $event
     */
    $scope.createTimeTool = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.createTimeOpened = true;
    };

}])
;

