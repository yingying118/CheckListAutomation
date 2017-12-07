/**
 * Created by azhang on 16/10/2017.
 */
(function () {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .controller('templateListController', templateListController);

    templateListController.$inject = ['templatesService'];

    function templateListController(templatesService) {
        var self = this;
        self.groups = [];
        self.templates=[];
        self.attributes=[];
        self.sections=[];
        self.selectedGroupID;
        self.selectedTemplate={};
        getAllGroups();

        this.getGroupTemplates = getGroupTemplates;
        this.getTemplateByID=getTemplateByID;
        this.isDropdownValue=isDropdownValue;
        this.isEmpty=isEmpty;
        /**
         * Public functions
         */



        function getTemplateByID(template) {
            console.log("template id:" + template.id);
            templatesService.getTemplateByID(template.id).then(function (response) {
                self.selectedTemplate = response.data;
                templatesService.sortTemplateSectionAttributes(self.selectedTemplate);
                console.log('templates get!');
                console.log('template content: ' + JSON.stringify(self.selectedTemplate));
            }, function (errResponse) {
                console.error('Error while getting the template attributes with tid : ' + selectedTemplate.id + ';' + errResponse.toString());
            })

        }
        function isDropdownValue(input){
            return input.toLowerCase()==="dropdown";
        }
        function getGroupTemplates(){
            self.selectedTemplate={};
            console.log("group id:" + self.selectedGroupID);
            console.log("fetching group templates");
            templatesService.getGroupTemplates(self.selectedGroupID).then(function (response){
                self.templates = response.data;
            }, function (errResponse){
                console.error('Error while getting the group templates with gid : ' + selectedGroupID + ';' + errResponse.toString());
            })
        }


        function isEmpty(obj) {
            for(var key in obj) {
                if(obj.hasOwnProperty(key))
                    return false;
            }
            return true;
        }

        /**
         * Private functions
         */



        function getAllGroups() {
            templatesService.getGroups().then(function (response) {
                    self.groups = response.data;

                    console.log(self.groups);

                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }


    }
})();
