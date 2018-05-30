/**
 * Created by 赵传志 on 2018/3/5.
 */

'use strict';
app.controller('deptListController', ['$scope', '$http', '$modal', 'toaster', function ($scope, $http, $modal, toaster) {
    
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
        data: 'depts',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'dept_number', displayName: '部门编号', width: '200px'},
            {field: 'dept_name', displayName: '部门名称', width: '200px'},
            {field: 'staff_number', displayName: '职员数量', width: '200px'},
            {
                field: 'remove',
                displayName: '操作',
                width: "300px",
                cellTemplate: 
                '<button class="btn btn-primary btn-sm m-t-xs m-l-xs" title="编辑" style="margin-top: 2px" ng-click="editRowIndex(row.entity)" ng-show="currentUser.roleValue==01">编辑</button>' +
                '<button class="btn btn-danger  btn-sm m-t-xs m-l-xs" title="解散" style="margin-top: 2px" ng-click="removeRowIndex(row.entity)" ng-show="currentUser.roleValue==01">解散</button>'
            }
        ]
    };

    $http.get("users/current").success(function (data) {
        $scope.currentUser = data.data;
    });
    
    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'depts?page=' + page + '&pageSize=' + pageSize;
        $http.get(url).success(function (data) {
            $scope.depts = data.data.list;
            console.log($scope.depts);
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
    
    $scope.search = function () {
        $scope.pagingOptions.currentPage = 1;
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, '');
    }

    /**
     * 创建
     */
    $scope.createDept = function () {
        var rtn = $modal.open({
            templateUrl: 'tpl/dept/create_dept.html',
            controller: 'createDeptController',
            resolve: {}
        });

        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '新增部门成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, function () {
        });
    }

    /**
     * 修改
     * @param entity
     */
    $scope.editRowIndex = function (entity) {
        var id = entity.id;
        var rtn = $modal.open({
            templateUrl: 'tpl/dept/update_dept.html',
            controller: 'updateDeptController',
            resolve: {
                id: function () {
                    return id;
                }
            }
        });
        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '修改部门名称成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, function () {
        });
    }

    /**
     * 解散
     * @param entity
     */
    $scope.removeRowIndex = function (entity) {
        $http.delete('depts/' + entity.id).success(function (data) {
            if (data.status == 'SUCCESS') {
                $scope.pop('success', '', '成功解散部门');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            } else {
                alert(data.error);
            }
        })

    }

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
