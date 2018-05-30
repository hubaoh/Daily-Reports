'use strict';

/**
 * Config for the router
 */
 angular.module('app')
.run(
    [          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;        
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider','JQ_CONFIG', 'MODULE_CONFIG',
      function ($stateProvider,   $urlRouterProvider,JQ_CONFIG,MODULE_CONFIG) {
          $urlRouterProvider.otherwise("/myReports");
          $stateProvider
              .state('user', {
                  url: '/user',
                  templateUrl: 'tpl/user/user_list.html',
                  resolve: load(['toaster','ngGrid','js/controllers/user/userList.js', 'js/controllers/user/createUser.js', 'js/controllers/user/seeUser.js', 'js/controllers/user/updateUser.js'])
                  }).state('dept', {
              url: '/dept',
              templateUrl: 'tpl/dept/dept_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/dept/deptList.js','js/controllers/dept/updateDept.js', 'js/controllers/dept/createDept.js'])
          }).state('role', {
              url: '/role',
              templateUrl: 'tpl/user/user_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/user/userList.js'])
          }).state('myReports', {
              url: '/myReports',
              templateUrl: 'tpl/myreports/myreports_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/myreports/myreportsList.js', 'js/controllers/myreports/createReport.js', 'js/controllers/myreports/seeMyReport.js', 'js/controllers/myreports/submitReport.js'])
          }).state('allReports', {
              url: '/allReports',
              templateUrl: 'tpl/allreports/allreports_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/allreports/allreportsList.js','js/controllers/allreports/seeAllReport.js'])
          }).state('reportChecking', {
              url: '/reportChecking',
              templateUrl: 'tpl/reportchecked/reportChecking_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/reportchecked/reportCheckingList.js','js/controllers/reportchecked/updateReportChecked.js'])
          }).state('myReportChecked', {
              url: '/myReportChecked',
              templateUrl: 'tpl/myreportchecked/myReportChecked_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/myreportchecked/myReportCheckedList.js','js/controllers/myreportchecked/seeMyReportChecked.js','js/controllers/myreportchecked/createReportAppeal.js'])
          }).state('myReportAppeal', {
              url: '/myReportAppeal',
              templateUrl: 'tpl/reportappeals/reportAppeal_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/reportappeals/reportAppealList.js','js/controllers/reportappeals/seeReportAppeals.js'])
          }).state('reportAppealing', {
              url: '/reportAppealing',
              templateUrl: 'tpl/reportappeals/reportAppealing_list.html',
              resolve: load(['toaster','ngGrid','js/controllers/reportappeals/reportAppealingList.js','js/controllers/reportappeals/seeReport.js','js/controllers/reportappeals/seeReportChecked.js','js/controllers/reportappeals/updateReportAppeal.js'])
          }).state('summary', {
              url: '/summary',
              templateUrl: 'tpl/reportsummary/report_summary.html',
              resolve: load(['toaster','js/controllers/reportsummary/reportSummary.js'])
          }).state('sort', {
              url: '/sort',
              templateUrl: 'tpl/scoresort/reportScoreSort.html',
              resolve: load(['toaster','js/controllers/scoresort/reportScoreSort.js'])
          }).state('personal', {
              url: '/personal',
              templateUrl: 'tpl/personalinf/personalInfo.html',
              resolve: load(['toaster','js/controllers/personalinfo/personalInfo.js','js/controllers/personalinfo/updateInfo.js','js/controllers/personalinfo/updatePassword.js'])
          }).state('file', {
              url: '/file',
              templateUrl: 'tpl/file/file_list.html',
              resolve: load(['toaster','js/controllers/file/fileList.js','js/controllers/file/fileUpload.js','js/controllers/file/seeFile.js'])
          }).state('left', {
              url: '/left',
              views: {
                  "userInfo":{
                      template: '<h1>left</h1>'
                  }
              }
              // templateUrl: 'tpl/user/user_list.html',

              // resolve: load(['toaster','ngGrid','js/controllers/user/userList.js'])
          });

          function load(srcs, callback) {
              return {
                  deps: ['$ocLazyLoad', '$q',
                      function( $ocLazyLoad, $q ){
                          var deferred = $q.defer();
                          var promise  = false;
                          srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
                          if(!promise){
                              promise = deferred.promise;
                          }
                          angular.forEach(srcs, function(src) {
                              promise = promise.then( function(){
                                  if(JQ_CONFIG[src]){
                                      return $ocLazyLoad.load(JQ_CONFIG[src]);
                                  }
                                  angular.forEach(MODULE_CONFIG, function(module) {
                                      if( module.name == src){
                                          name = module.name;
                                      }else{
                                          name = src;
                                      }
                                  });
                                  return $ocLazyLoad.load(name);
                              } );
                          });
                          deferred.resolve();
                          return callback ? promise.then(function(){ return callback(); }) : promise;
                      }]
              }
          }

      }
    ]
  );
