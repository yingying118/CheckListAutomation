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
        self.selectedGroupID;
        self.selectedTemplate=null;
        getAllGroups();

        this.getGroupTemplates = getGroupTemplates;
        this.getTemplateAttributes = getTemplateAttributes;
        /**
         * Public functions
         */


        function getTemplateAttributes(template){
            self.selectedTemplate = template;
            console.log("template id:" + template.id);
            templatesService.getTemplateAttributes(template.id).then(function (response){
                self.attributes = response.data;
                console.log('templates info:' + JSON.stringify(self.attributes));
            }, function (errResponse){
                console.error('Error while getting the template attributes with tid : ' + template.id + ';' + errResponse.toString());
            })

        }

        function getGroupTemplates(){
            self.attributes=[];

            console.log("group id:" + self.selectedGroupID);
            console.log("fetching group templates");
            templatesService.getGroupTemplates(self.selectedGroupID).then(function (response){
                self.templates = response.data;
                console.log('templates info:' + JSON.stringify(self.templates));

            }, function (errResponse){
                console.error('Error while getting the group templates with gid : ' + selectedGroupID + ';' + errResponse.toString());
            })
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
