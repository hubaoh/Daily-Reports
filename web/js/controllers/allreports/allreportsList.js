/**
 * Created by 赵传志 on 2018/3/7.
 */

'use strict';
app.controller('allReportsListController', ['$scope', '$http', '$modal', 'toaster','$filter',function ($scope, $http, $modal, toaster, $filter) {
    $scope.report_time = $filter('date')(new Date(), 'yyyy-MM-dd');
    $scope.userName = "";

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
        data: 'allreports',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'report_time', displayName: '日报时间', width: '300px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.report_time | date:"yyyy-MM-dd" }}</div>'},
            {field: 'report_writer', displayName: '员工姓名', width: '300px'},
            {
                field: 'remove',
                displayName: '操作',
                width: "300px",
                cellTemplate:
                // '<button class="btn btn-primary btn-sm m-t-xs m-l-xs" title="写日报" style="margin-top: 2px" ng-click="editRowIndex(row.entity)">写日报</button>' +
                '<button class="btn btn-danger  btn-sm m-t-xs m-l-xs" title="查看日报" style="margin-top: 2px; margin-left: 10px" ng-click="seeRowIndex(row.entity)">查看日报</button>'
            }
        ]
    };

    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'reports?page=' + page + '&pageSize=' + pageSize+ '&report_time=' + $filter('date')($scope.report_time, 'yyyy-MM-dd');
        if ($scope.userName != "") {
            url += '&userName=' + $scope.userName;
        }
        console.log(url);
        $http.get(url).success(function (data) {
            $scope.allreports = data.data.list;
            console.log($scope.allreports);
            $scope.totalServerItems = data.data.totalElements;
            if($scope.allreports.length == 0) {
                $scope.pop('warning', '警告', '暂时没有员工提交日报！');
            }
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
    $scope.$watch('report_time', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.search = function () {
        $scope.pagingOptions.currentPage = 1;
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, '');
    };


    /**
     * 查看日报
     * @param entity
     */
    $scope.seeRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/myreports/see_report.html',
            controller: 'seeAllReportController',
            resolve: {
                report: function () {
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

