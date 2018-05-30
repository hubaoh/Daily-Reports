/**
 * Created by 赵传志 on 2018/3/25.
 */

'use strict';
app.controller('reportAppealingListController', ['$scope', '$http', '$modal', 'toaster', '$filter', function ($scope, $http, $modal, toaster, $filter) {



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
        data: 'reportAppealDtos',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'report_writer', displayName: '申诉人', width: '100px'},
            {field: 'report_time', displayName: '日报日期', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col5 colt5" >{{row.entity.report_time | date:"yyyy年MM月dd日" }}</div>'},
            {field: 'handlingScore', displayName: '处理前分数', width: '100px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" style="color: red;">{{row.entity.handlingScore +"分" }}</div>'},
            {field: 'checker', displayName: '考评人', width: '100px'},
            {field: 'checked_time', displayName: '考评时间', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.checked_time | date:"yyyy年MM月dd日" }}</div>'},
            {
                field: 'remove',
                displayName: '操作',
                width: "200px",
                cellTemplate:
                    '<button class="btn btn-info  btn-sm m-t-xs m-l-xs" title="日报详情" style="margin-top: 2px; margin-left: 10px" ng-click="seeReportRowIndex(row.entity)">日报详情</button>'
                    +'<button class="btn btn-primary  btn-sm m-t-xs m-l-xs" title="考核详情" style="margin-top: 2px; margin-left: 10px" ng-click="seeReportCheckedRowIndex(row.entity)">考核详情</button>'
                    +'<button class="btn btn-success  btn-sm m-t-xs m-l-xs" title="申诉处理" style="margin-top: 2px; margin-left: 10px" ng-click="updateRowIndex(row.entity)">申诉处理</button>'
            }
        ]
    };


    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'report_appeals/appealing?page=' + page + '&pageSize=' + pageSize;

        $http.get(url).success(function (data) {
            $scope.reportAppealDtos = data.data.list;
            $scope.totalServerItems = data.data.totalElements;
            console.log($scope.reportAppealDtos);
            // formatData($scope.reportAppealDtos);

        });

    };

    /**
     * 格式化数据
     * @param reportAppealings
     */
    function formatData(datas) {
        angular.forEach(datas, function (data) {
            // if (data.handlingScore == null) {
            //     data.handlingScore = "_ _";
            // }
            // if (data.appeal_time == null) {
            //     data.appeal_time = "_ _";
            // }
            // if (data.appealer == null) {
            //     data.appealer = "_ _";
            // }
            if (data.appeal_status == "01") {
                data.appeal_status = "待处理";
            } else if (data.appeal_status == "02") {
                data.appeal_status = "已处理";
            }else if (data.appeal_status == "03") {
                data.appeal_status = "已驳回";
            }
        })
    }

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
     * 查看日报详情
     * @param entity
     */
    $scope.seeReportRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/reportappeals/see_Report.html',
            controller: 'seeReportController',
            resolve: {
                reportAppealDto: function () {
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
     * 查看日报考评详情
     * @param entity
     */
    $scope.seeReportCheckedRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/reportappeals/see_ReportChecked.html',
            controller: 'seeReportCheckedController',
            resolve: {
                reportAppealDto: function () {
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
     * 申诉处理
     * @param entity
     */
    $scope.updateRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/reportappeals/update_ReportAppeal.html',
            controller: 'updateReportAppealController',
            resolve: {
                reportAppealDto: function () {
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
