(function () {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .controller('templatesController', templatesController);

    templatesController.$inject = ['templatesService'];

    function templatesController(templatesService) {
        var self = this;
        self.groups = [];
        self.attributes=[];
        self.group={};
        self.selectedGroup=null;
        self.newTemplate = {};
        self.newAttributeSelection = null;

        self.attributeSelectionTemplate = {
            searchPlaceHolder: 'Typing Attribute Name to filter.',
            labelAll: 'All Attributes',
            labelSelected: 'Selected Attributes',
            labelShow: 'name',
            orderProperty: 'name',
            items: [],
            selectedItems: []
        };



        getAllGroups();
        getAllAttributes();

        this.getAllAttributes = getAllAttributes;
        this.createNewTemplate = createNewTemplate;
        this.resetNewTemplate = resetNewTemplate;
        this.getGroupById = getGroupById;
        this.getNewTemplateInfo = getNewTemplateInfo;

        /**
         * Public functions
         */
        function resetNewTemplate() {
            self.newTemplate = {};
            self.newAttributeSelection = angular.copy(self.attributeSelectionTemplate);
            self.newAttributeSelection.items = angular.copy(self.attributes);
            self.newAttributeSelection.selectedItems = [];
        }

        function getNewTemplateInfo(){
            self.newTemplate.attributes = self.newAttributeSelection.selectedItems;
            self.newTemplate.groupObject =  self.group;

        }

        function createNewTemplate() {

            templatesService.createTemplate(self.newTemplate).then(
                function (response) {
                    console.log('new template:' + JSON.stringify(self.newTemplate));
                    resetNewTemplate();
                },
                function (errResponse) {
                    console.error('Error while creating project: ' + errResponse.data.message);
                }
            );
        };





        /**
         * Private functions
         */

        function getGroupById(){
            console.log("group id:" + self.selectedGroup);
            templatesService.getGroupById( self.selectedGroup).then(function (response) {
                    self.group = response.data;

                    console.log('group info:' + JSON.stringify(self.group));

                }, function (errResponse) {
                    console.error('Error while getting the group with id : ' + selectedGroup + ';' + errResponse.toString());
                }
            )

        }

        function getAllGroups() {
            templatesService.getGroups().then(function (response) {
                    self.groups = response.data;

                    console.log(self.groups);

                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }

        function getAllAttributes() {
            templatesService.getAllAttributes().then(function (response) {
                    self.attributes = response.data;

                    resetNewTemplate();
                }, function (errResponse) {
                    console.error('Error while get all attributes : ' + errResponse.data.message);
                }
            )
        }
    }
})();
