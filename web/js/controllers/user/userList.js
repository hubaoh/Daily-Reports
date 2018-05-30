/**
 * Created by 赵传志 on 2018/1/26.
 */
'use strict';
app.controller('userListController', ['$scope', '$http', '$modal', 'toaster', function ($scope, $http, $modal, toaster) {
    $scope.employeeNumber = "";
    $scope.userName = "";
    $scope.dept = "";

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
        data: 'users',
        enablePaging: true,
        showFooter: true,
        rowHeight: 41,
        headerRowHeight: 36,
        multiSelect: false,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n:'zh-cn',
        columnDefs: [
            {field: 'employeeNumber', displayName: '工号', width: '100px'},
            {field: 'userName', displayName: '用户名', width: '100px'},
            {field: 'deptName', displayName: '所属部门', width: '100px'},
            {field: 'role.roleName', displayName: '用户权限', width: '100px'},
            {field: 'phoneNumber', displayName: '手机号码', width: '200px'},
            {
                field: 'remove',
                displayName: '操作',
                width: "300px",
                cellTemplate: '<button class="btn btn-primary btn-sm m-t-xs m-l-xs" title="编辑" style="margin-top: 2px" ng-click="editRowIndex(row.entity)" ng-show="currentUser.roleValue==01">编辑</button>' +
                '<button class="btn btn-info  btn-sm m-t-xs m-l-xs" title="详情"  style="margin-top: 2px" ng-click="seeRowIndex(row.entity)">详情</button>' +
                '<button class="btn btn-danger  btn-sm m-t-xs m-l-xs" title="删除" style="margin-top: 2px" ng-click="removeRowIndex(row.entity)" ng-show="currentUser.roleValue==01">删除</button>'
            }
        ]
    };

    $http.get("users/current").success(function (data) {
        $scope.currentUser = data.data;
    });

  $http.get("depts/all").success(function (data) {
        var deptInfo = {
            "id":0,
            "dept_name":"全部"
        }
        $scope.depts = data.data;
        console.log($scope.depts);
        $scope.depts.push(deptInfo);
        $scope.dept = $scope.depts[$scope.depts.length - 1];
    })

    $scope.getPagedDataAsync = function (pageSize, page) {
        var url = 'users?page=' + page + '&pageSize=' + pageSize;
        if ($scope.dept != "" || $scope.dept != undefined) {
            url += '&deptName=' + $scope.dept.dept_name;
        }
        if ($scope.employeeNumber != "") {
            url += '&employeeNumber=' + $scope.employeeNumber;
        }
        if ($scope.userName != "") {
            url += '&userName=' + $scope.userName;
        }
        $http.get(url).success(function (data) {
            $scope.users = data.data.list;
            console.log($scope.users);
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

    $scope.$watch('dept', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.search = function () {
        $scope.pagingOptions.currentPage = 1;
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, '');
    }

    $scope.createUser = function () {
        var rtn = $modal.open({
            templateUrl: 'tpl/user/create_user.html',
            controller: 'createUserController',
            resolve: {}
        });

        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '新增用户成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            } 
        }, function () {
        });
    }

    $scope.seeRowIndex = function (entity) {
        var rtn = $modal.open({
            templateUrl: 'tpl/user/see_user.html',
            controller: 'seeUserController',
            resolve: {
                userDto: function () {
                    return entity
                }
            }
        });
        rtn.result.then(function (status) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }, function () {
        });
    }

    $scope.editRowIndex = function (entity) {
        var id = entity.id;
        var rtn = $modal.open({
            templateUrl: 'tpl/user/update_user.html',
            controller: 'updateUserController',
            resolve: {
                userId: function () {
                    return id;
                }
            }
        });
        rtn.result.then(function (status) {
            if (status == 'SUCCESS') {
                $scope.pop('success', '', '修改用户信息成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, function () {
        });
    }

    $scope.removeRowIndex = function (entity) {
        $http.delete('users/' + entity.id).success(function (data) {
            if (data.status == 'SUCCESS') {
                $scope.pop('success', '', '删除成功');
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            } else {
                alert(data.error);
            }
        })

    }

    // 将数据导出到excel中
    var myStyle = {
        headers: true,
        caption: {
            title:'在职员工表'
        },
        column: {
            style:'font-size:15px'
        },
        columns: [
            {columnid:'employeeNumber',title: '员工编号',width:'100px'},
            {columnid:'userName', title: '用户名', width:'100px'},
            {columnid:'deptName', title:'所属部门', width:'150px'},
            {columnid:'roleName', title:'用户权限', width:'150px'},
            {columnid:'phoneNumber', title:'手机号码', width:'150px'}
            ],
    };

    // 获得要导出到excel中的数据
    $scope.exportData = function (){
            alasql('SELECT * INTO XLS("在职员工表.xls",?) FROM ?', [myStyle,   $scope.users]);
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