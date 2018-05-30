/**
 * Created by 赵传志 on 2018/3/7.
 */

'use strict';
app.controller('myReportsListController', ['$scope', '$http', '$modal', 'toaster', '$filter', function ($scope, $http, $modal, toaster, $filter) {

    $scope.report_time = "";
    $scope.writeTime = new Date();

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
        data: 'reports',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'report_time', displayName: '日期', width: '300px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.report_time | date:"yyyy年MM月dd日" }}</div>'},
            {field: 'report_status', displayName: '状态', width: '300px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.report_status | formatStatus }}</div>'},
            {
                field: 'remove',
                displayName: '操作',
                width: "300px",
                cellTemplate:
                '<button class="btn btn-primary btn-sm m-t-xs m-l-xs" title="日报提交" style="margin-top: 2px" ng-click="editRowIndex(row.entity)" ng-disabled="">日报提交</button>' +
                '<button class="btn btn-danger  btn-sm m-t-xs m-l-xs" title="查看日报" style="margin-top: 2px; margin-left: 10px" ng-click="seeRowIndex(row.entity)">查看日报</button>'
            }
        ]
    };

    app.filter('formatStatus', function() {
        return function(type) {
            if ("01" == type) {
                return "未提交"
            } else if ("02" == type) {
                return "已提交"
            } else if ("03" == type) {
                return "补写"
            } 
        }
    });
    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'reports/personal?page=' + page + '&pageSize=' + pageSize;
        if ($scope.report_time != "" && $scope.report_time != null ){
            url += '&report_time=' + $filter('date')($scope.report_time,'yyyy-MM-dd');
        }
        console.log(url);
        $http.get(url).success(function (data) {
            $scope.reports = data.data.list;
            console.log($scope.reports);
            $scope.totalServerItems = data.data.totalElements;
            // $scope.isWriteReport($scope.reports);
        });

    };
    // // 设置写日报按钮失效
    // $scope.isWriteReport = function(reports){
    //     $scope.newDate = $filter('date')($scope.writeTime,'yyyyMMdd');
    //     angular.forEach(reports,function (report) {
    //         $scope.oldDate = $filter('date')(report.report_time,'yyyyMMdd');
    //         if ($scope.newDate > $scope.oldDate ){
    //             $scope.isWrite = true;
    //         }
    //     });
    // };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, "");



    // 定时
    // setInterval(function () {
    // }, 2000);

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
    }

    /**
     * 创建日报
     */
    $scope.createReport = function () {
        var rtn = $modal.open({
            templateUrl: 'tpl/myreports/create_report.html',
            controller: 'createReportController',
            resolve: {}
        });

        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '日报保存成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, function () {
        });
    };

    /**
     * 提交日报
     * @param entity
     */
    $scope.editRowIndex = function (entity) {
        var id = entity.id;
        var rtn = $modal.open({
            templateUrl: 'tpl/myreports/update_report.html',
            controller: 'submitReportController',
            resolve: {
                id: function () {
                    return id;
                }
            }
        });
        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '日报提交成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, function () {
        });
    };

    /**
     * 查看日报
     * @param entity
     */
    $scope.seeRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/myreports/see_report.html',
            controller: 'seeMyReportController',
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
