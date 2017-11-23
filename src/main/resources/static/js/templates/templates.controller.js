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
        //self.newAttributeSelection = null;
        self.newSections=[];


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
        getNonStaticAttributes();
        initPage();

        this.createNewTemplate = createNewTemplate;
        this.resetNewTemplate = resetNewTemplate;
        this.getGroupById = getGroupById;
        this.getNewTemplateInfo = getNewTemplateInfo;
        this.addNewSection=addNewSection;
        this.removeSection = removeSection;
        /**
         * Public functions
         */
        function addNewSection(){
            var newSectionNo = self.newSections.length + 1;
            var temp = {};
            temp.order= newSectionNo;
            self.newSections.push(temp);

            temp.selectAttribute = angular.copy(self.attributeSelectionTemplate);
            temp.selectAttribute.items =  self.newSections[0].selectAttribute.items;
            temp.selectAttribute.selectedItems=[];


        }

        function removeSection(){
            var lastItem = self.newSections.length-1;
            self.newSections.splice(lastItem);
        }

        function resetNewTemplate() {
            self.newTemplate = {};
            /**
            self.newAttributeSelection = angular.copy(self.attributeSelectionTemplate);
            self.newAttributeSelection.items = angular.copy(self.attributes);
            self.newAttributeSelection.selectedItems = [];**/
            initPage();
            self.newSections[0].selectAttribute = angular.copy(self.attributeSelectionTemplate);
            self.newSections[0].selectAttribute.items = angular.copy(self.attributes);
            self.newSections[0].selectAttribute.selectedItems=[];

        }

        function getNewTemplateInfo(){
            self.newTemplate.groupObject = self.group;
            self.newTemplate.sections=[];
            self.newSections.forEach(function(obj){
                var toSave={};
                toSave.section_order=obj.order;
                toSave.name= obj.name;
                toSave.sectionAttributes=[];
                obj.selectAttribute.selectedItems.forEach(function(attrObj){
                    var temp_sectionAttribute={};
                    temp_sectionAttribute.attribute=attrObj;
                    toSave.sectionAttributes.push(temp_sectionAttribute)
                });
                self.newTemplate.sections.push(toSave);
            });
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
        }





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

        function initPage(){
            self.newSections=[
                {order: '1', name: 'Overview', selectAttribute:[]}
            ];
            self.group={};
            self.selectedGroup=null;

        }

        function getNonStaticAttributes() {
            templatesService.getNonStaticAttributes().then(function (response) {
                    self.attributes = response.data;
                    resetNewTemplate();
                }, function (errResponse) {
                    console.error('Error while get all attributes : ' + errResponse.data.message);
                }
            )
        }
    }
})();
