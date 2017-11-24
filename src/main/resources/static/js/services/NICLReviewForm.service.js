/**
 * Created by azhang on 11/10/2017.
 */

(function() {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .factory('reviewFormService', reviewFormService);

    reviewFormService.$inject = ['$http'];
    function reviewFormService($http) {

        var urlBase = "/NICL";
        var services = {

        };

        return services;


    }





})();