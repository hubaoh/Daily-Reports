/**
 * Created by 赵传志 on 2018/3/14.
 */

'use strict';
app.controller('reportAppealListController', ['$scope', '$http', '$modal', 'toaster', '$filter', function ($scope, $http, $modal, toaster, $filter) {



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
        data: 'reportAppeals',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'report_time', displayName: '日报日期', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col5 colt5" >{{row.entity.report_time | date:"yyyy年MM月dd日" }}</div>'},
            {field: 'appeal_status', displayName: '申诉状态', width: '100px'},
            {field: 'handlingScore', displayName: '处理前分数', width: '150px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" style="color: red;">{{row.entity.handlingScore +"分" }}</div>'},
            {field: 'handledScore', displayName: '处理后分数', width: '150px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" style="color: green;">{{row.entity.handledScore +"分" }}</div>'},
            {field: 'appealer', displayName: '审核人', width: '100px'},
            {field: 'appeal_time', displayName: '审核时间', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col5 colt5" >{{row.entity.appeal_time | date:"yyyy年MM月dd日" }}</div>'},
            {
                field: 'remove',
                displayName: '操作',
                width: "200px",
                cellTemplate:
                '<button class="btn btn-danger  btn-sm m-t-xs m-l-xs" title="详情" style="margin-top: 2px; margin-left: 10px" ng-click="seeRowIndex(row.entity)">详情</button>'
            }
        ]
    };


    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'report_appeals?page=' + page + '&pageSize=' + pageSize;

        $http.get(url).success(function (data) {
            $scope.reportAppeals = data.data.list;
            $scope.totalServerItems = data.data.totalElements;
            formatData($scope.reportAppeals);

        });

    };

    /**
     * 格式化数据
     * @param reportCheckeds
     */
    function formatData(reportAppeals) {
        angular.forEach(reportAppeals, function (reportAppeal) {
            if (reportAppeal.handledScore == null) {
                reportAppeal.handledScore = "_ _";
            }
            if (reportAppeal.appeal_time == null) {
                reportAppeal.appeal_time = "_ _";
            }
            if (reportAppeal.appealer == null) {
                reportAppeal.appealer = "_ _";
            }
            if (reportAppeal.appeal_status == "01") {
                reportAppeal.appeal_status = "待处理";
            } else if (reportAppeal.appeal_status == "02") {
                reportAppeal.appeal_status = "已处理";
            }else if (reportAppeal.appeal_status == "03") {
                reportAppeal.appeal_status = "已驳回";
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
     * 查看日报申诉详情
     * @param entity
     */
    $scope.seeRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/reportappeals/see_reportAppeal.html',
            controller: 'seeMyReportAppealController',
            resolve: {
                id: function () {
                    return entity.id
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
