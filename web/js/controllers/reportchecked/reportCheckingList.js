/**
 * Created by 赵传志 on 2018/3/10.
 */

'use strict';
app.controller('reportCheckingListController', ['$scope', '$http', '$modal', 'toaster', '$filter', function ($scope, $http, $modal, toaster, $filter) {



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
        data: 'reportCheckedings',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'report_time', displayName: '日报日期', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.report_time | date:"yyyy年MM月dd日" }}</div>'},
            {field: 'report_writer', displayName: '员工姓名', width: '200px'},
            {field: 'report_status', displayName: '日报状态', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.report_status | formatReportStatus }}</div>'},
            {field: 'reportChecked_status', displayName: '考评状态', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.reportChecked_status | formatStatus }}</div>'},
            {
                field: 'remove',
                displayName: '操作',
                width: "300px",
                cellTemplate:
                '<button class="btn btn-primary btn-sm m-t-xs m-l-xs" title="查看日报" style="margin-top: 2px" ng-click="seeRowIndex(row.entity)" ng-disabled="isWrite">查看日报</button>' +
                '<button class="btn btn-danger  btn-sm m-t-xs m-l-xs" title="日报考评" style="margin-top: 2px; margin-left: 10px" ng-click="editRowIndex(row.entity)">日报考评</button>'
            }
        ]
    };

    app.filter('formatStatus', function() {
        return function(type) {
            if ("01" == type) {
                return "未考评"
            } else if ("02" == type) {
                return "已考评"
            } else if ("03" == type) {
                return "申诉中"
            }
        }
    });

    app.filter('formatReportStatus', function() {
        return function(type) {
            if ("01" == type) {
                return "未提交"
            } else if ("02" == type) {
                return "当日日报"
            } else if ("03" == type) {
                return "补写日报"
            }
        }
    });

    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'report_checkeds/reportChecking?page=' + page + '&pageSize=' + pageSize;
        $http.get(url).success(function (data) {
            $scope.reportCheckedings = data.data.list;
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
     * 日报考评
     * @param entity
     */
    $scope.editRowIndex = function (entity) {
        var id = entity.id;
        var rtn = $modal.open({
            templateUrl: 'tpl/reportchecked/update_reportChecked.html',
            controller: 'updateReportCheckedController',
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
