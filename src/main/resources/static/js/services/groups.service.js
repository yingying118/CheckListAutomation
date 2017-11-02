/**
 * Created by azhang on 06/10/2017.
 */
(function() {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .factory('groupsService', groupsService);

    groupsService.$inject = ['$http'];

    function groupsService($http) {
        var urlBase = "/groups";
        var services = {
            getGroups: getGroups,
            createGroup: createGroup,
            deleteGroup: deleteGroup
        };

        return services;

        function getGroups() {
            console.log('get Groups');
            var url = urlBase + '/getallgroups';
            return $http.get(url);
        }

        function createGroup(group) {

            console.log('create group');
            var url = urlBase + '/creategroup';

            var config = {
                headers: {
                    'Content-Type': 'application/json;'
                }
            };
            return $http.post(url, group, config);
        }

        function deleteGroup(group) {
            console.log("delete group " + group.id);
            var url = urlBase + '/removegroup/' + group.id;
            return $http.delete(url);
        }
    }
})();