/**
 * Created by 赵传志 on 2018/3/10.
 */

'use strict';
app.controller('allReportCheckedListController', ['$scope', '$http', '$modal', 'toaster', '$filter', function ($scope, $http, $modal, toaster, $filter) {



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
        data: 'myReportChecked',
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
            {field: 'reportChecked_status', displayName: '考评状态', width: '150px'},
            {field: 'totalScore', displayName: '考评分数', width: '150px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" style="color: red;">{{row.entity.totalScore +"分" }}</div>'},
            {field: 'checker', displayName: '考评人', width: '150px'},
            {field: 'checked_time', displayName: '考评时间', width: '200px', cellTemplate: '<div class="ngCellText ng-scope col4 colt4" >{{row.entity.checked_time | date:"yyyy年MM月dd日" }}</div>'},
            {
                field: 'remove',
                displayName: '操作',
                width: "300px",
                cellTemplate:
                '<button class="btn btn-primary btn-sm m-t-xs m-l-xs" title="详情" style="margin-top: 2px" ng-click="seeRowIndex(row.entity)" ng-disabled="isWrite">详情</button>' +
                '<button class="btn btn-danger  btn-sm m-t-xs m-l-xs" title="申诉" style="margin-top: 2px; margin-left: 10px" ng-click="createRowIndex(row.entity)">申诉</button>'
            }
        ]
    };

    // app.filter('formatStatus', function() {
    //     return function(type) {
    //         if ("01" == type) {
    //             return "未考评"
    //         } else if ("02" == type) {
    //             return "已考评"
    //         } else if ("03" == type) {
    //             return "申诉中"
    //         }
    //     }
    // });
    
    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'report_checkeds/all?page=' + page + '&pageSize=' + pageSize;
        
        $http.get(url).success(function (data) {
            $scope.myReportChecked = data.data.list;
            $scope.totalServerItems = data.data.totalElements;
            formatData($scope.myReportChecked);
            console.log($scope.myReportChecked);
        });

    };

    /**
     * 格式化数据
     * @param reportCheckeds
     */
    function formatData(reportCheckeds) {
        angular.forEach(reportCheckeds, function (reportChecked) {
            if (reportChecked.totalScore == null) {
                reportChecked.totalScore = "_ _";
            }
            if (reportChecked.checked_time == null) {
                reportChecked.checked_time = "_ _";
            }
            if (reportChecked.checker == null) {
                reportChecked.checker = "_ _";
            }
            if (reportChecked.reportChecked_status == "01") {
                reportChecked.reportChecked_status = "未考评";
            } else if (reportChecked.reportChecked_status == "02") {
                reportChecked.reportChecked_status = "已考评";
            }else if (reportChecked.reportChecked_status == "03") {
                reportChecked.reportChecked_status = "申诉中";
            }else if (reportChecked.reportChecked_status == "04") {
                reportChecked.reportChecked_status = "已处理";
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
     * 申诉
     */
    $scope.createRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/myreportchecked/create_reportAppeal.html',
            controller: 'createReportAppealController',
            resolve: {
                reportCheckedDto: function () {
                    return entity
                }
            }
        });

        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '日报申诉成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, function (
            
        ) {
        });
    };

    /**
     * 查看考评
     * @param entity
     */
    $scope.seeRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/myreportchecked/see_myReportChecked.html',
            controller: 'seeMyReportCheckedController',
            resolve: {
                reportCheckedDto: function () {
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
