/**
 * Created by azhang on 16/10/2017.
 */
/**
 * Created by azhang on 11/10/2017.
 */

(function() {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .factory('NICLService', NICLService);

    NICLService.$inject = ['$http','$location'];
    function NICLService($http,$location) {
        var urlBase = "/NICL";
        var services = {
            getGroups: getGroups,
            getGroupById: getGroupById,
            getAllAttributes:getAllAttributes,
            getGroupTemplates: getGroupTemplates,
            getTemplateAttributes: getTemplateAttributes,
            getTemplateByID:getTemplateByID,
            getTemplateName:getTemplateName,
            createNICLHead: createNICLHead,
            createNICLContent:createNICLContent,
            createNICLContentSet:createNICLContentSet,
            changeView:changeView,
            getNICLHeadByGroupID:getNICLHeadByGroupID,
            getNICLContentByHeadID:getNICLContentByHeadID,
            getNICLContentWithoutValueByHeadID:getNICLContentWithoutValueByHeadID,

        };

        return services;
        /* CRUD Services */

        function createNICLHead(NICLHead){
            console.log('create NICLHead');
            var url = urlBase + '/createniclhead';
            var config = {
                headers: {
                    'Content-Type': 'application/json;'
                }
            };
            return $http.post(url, NICLHead, config);
        }

        function createNICLContent(NICLContent){
            console.log('create NICLContent');
            var url = urlBase + '/createniclcontent';
            var config = {
                headers: {
                    'Content-Type': 'application/json;'
                }
            };
            return $http.post(url, NICLContent, config);

        }

        function createNICLContentSet(NICLContentSet){
            console.log('create NICLContentSet');
            var url = urlBase + '/createniclcontentset';
            var config = {
                headers: {
                    'Content-Type': 'application/json;'
                }
            };
            return $http.post(url, NICLContentSet, config);

        }

        /* get Groups funcs */
        function getGroups() {
            console.log('get Groups');
            var url = urlBase + '/getallgroups';
            return $http.get(url);
        }
        function getGroupById(groupID){
            console.log('get group with id ' + groupID);
            var url = urlBase + '/getgroupbyid/' + groupID;
            return $http.get(url);
        }
        /* get Attributes funcs */

        function getAllAttributes() {
            console.log('get Attributes');
            var url = urlBase + '/getallattributes';
            return $http.get(url);
        }
        function getTemplateAttributes(templateID){
            var url = urlBase + '/gettemplateattributes/' + templateID;
            return $http.get(url);
        }
        /* get Template funcs */
        function getGroupTemplates(groupID){
            console.log('get group template with gid: ' + groupID);
            var url = urlBase + '/getgrouptemplates/' + groupID;
            return $http.get(url);
        }
        function getNICLHeadByGroupID(groupID){
            console.log('get NICLHead with gid: ' + groupID);
            var url = urlBase + '/getniclheadbygroupid/' + groupID;
            return $http.get(url);
        }

        function getTemplateByID(templateID){
            var url = urlBase + '/gettemplatebyid/' + templateID;
            return $http.get(url);
        }
        function getTemplateName(templateID){
            var url = urlBase + '/gettemplatename/' + templateID;
            return $http.get(url);

        }
        function getNICLContentByHeadID(hid){
            console.log('get NICLContent with hid: ' + hid);
            var url = urlBase + '/getniclcontentbyhid/' + hid;
            return $http.get(url);
        }

        function getNICLContentWithoutValueByHeadID(hid){
            console.log('get NICLContent with hid: ' + hid);
            var url = urlBase + '/getniclcontentwithoutvaluebyhid/' + hid;
            return $http.get(url);
        }

        function changeView(url){
            $location.url(url);
        }
    }



})();