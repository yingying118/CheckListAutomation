/**
 * Created by azhang on 06/10/2017.
 */

var app = angular.module('checklistAutomationApp', ['multiselectcomp','ngRoute']);

app.config(function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    $routeProvider
        .when('/groups',{
            templateUrl:"./js/groups/groups.html",
            controller:'groupsController as groupsCtrl'
        }).when('/attributes',{
            templateUrl:"./js/attributes/attributes.html",
            controller:'attributesController as attrCtrl'
    }).
    when('/templates',{
        templateUrl:"./js/templates/templateGenerator/templates.html",
        controller:'templatesController as tempCtrl'}).
    when('/templateList',{
        templateUrl:"./js/templates/templateList/templateList.html",
        controller:'templateListController as tempListCtrl'}).
    when('/NICL',{
        templateUrl:"./js/NICL/NICLGenerator/NICL.html",
        controller:'NICLController as NICLCtrl'
    }).
    when('/NICLList',{
        templateUrl:"./js/NICL/NICLList/NICLList.html",
        controller:'NICLListController as NICLListCtrl'
    }).
    when('/reviewForm',{
        templateUrl:"./js/NICL/NICLReviewForm/NICLReviewForm.html",
        controller:'NICLReviewFormController as reviewFormCtrl'
    })
        .otherwise({redirectTo: '/'});

});