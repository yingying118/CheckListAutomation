/**
 * Created by azhang on 11/10/2017.
 */

(function() {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .factory('templatesService', templatesService);

    templatesService.$inject = ['$http'];
    function templatesService($http) {

        var urlBase = "/templates";
        var services = {
            getTemplates: getTemplates,
            createTemplate: createTemplate,
            updateTemplate:updateTemplate,
            deleteTemplate: deleteTemplate,
            getGroups: getGroups,
            getGroupById: getGroupById,
            getAllAttributes:getAllAttributes,
            getGroupTemplates: getGroupTemplates,
            getTemplateAttributes: getTemplateAttributes,
            getNonStaticAttributes:getNonStaticAttributes,
            getTemplateByID:getTemplateByID,
            sortTemplateSectionAttributes:sortTemplateSectionAttributes,
        };

        return services;

        function getTemplates() {
            console.log('get templates');
            var url = urlBase + '/getalltemplates';
            return $http.get(url);
        }
        function getTemplateByID(tid){
            var url = urlBase + '/gettemplatebyid/' + tid;
            return $http.get(url);
        }

        function createTemplate(template) {
            console.log('create template');
            var url = urlBase + '/createtemplate';
            var config = {
                headers: {
                    'Content-Type': 'application/json;'
                }
            };
            return $http.post(url, template, config);
        }

        function updateTemplate(template) {
            console.log('update template');
            console.log('template id: ' + template.id);
            var url = urlBase + '/updatetemplate/' + template.id;
            return $http.put(url, template);
        }


        function deleteTemplate(template) {
            console.log("delete template " + template.id);
            var url = urlBase + '/removetemplate/' + template.id;
            return $http.delete(url);
        }

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

        function getAllAttributes() {
            console.log('get Attributes');
            var url = urlBase + '/getallattributes';
            return $http.get(url);
        }
        function getNonStaticAttributes(){
            console.log('get Non Static Attributes');
            var url = urlBase + '/getnonstaticattributes';
            return $http.get(url);
        }

        function getGroupTemplates(groupID){
            console.log('get group template with gid: ' + groupID);
            var url = urlBase + '/getgrouptemplates/' + groupID;
            return $http.get(url);
        }
        function getTemplateAttributes(templateID){
            var url = urlBase + '/gettemplateattributes/' + templateID;
            return $http.get(url);
        }
        function sortTemplateSectionAttributes(templateData){
            templateData.sections.forEach(function (section) {
                section.sectionAttributes.sort(function(sa1,sa2){return sa1.order - sa2.order});
            })
        }
    }





})();