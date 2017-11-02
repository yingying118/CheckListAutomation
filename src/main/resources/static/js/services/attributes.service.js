/**
 * Created by azhang on 10/10/2017.
 */
/**
 * Created by azhang on 06/10/2017.
 */
(function() {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .factory('attributesService', attributesService);

    attributesService.$inject = ['$http'];

    function attributesService($http) {
        var urlBase = "/attributes";
        var services = {
            getAllAttributes: getAllAttributes,
            createAttribute: createAttribute,
            deleteAttribute: deleteAttribute
        };

        return services;

        function getAllAttributes() {
            console.log('get Attributes');
            var url = urlBase + '/getallattributes';
            return $http.get(url);
        }

        function createAttribute(attribute) {

            console.log('create Attribute');
            var url = urlBase + '/createattribute';
            var config = {
                headers: {
                    'Content-Type': 'application/json;'
                }
            };
            return $http.post(url, attribute, config);
        }

        function deleteAttribute(attribute) {
            console.log("delete Attribute " + attribute.id);
            var url = urlBase + '/removeattribute/' + attribute.id;
            return $http.delete(url);
        }
    }
})();