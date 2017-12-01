/**
 * Created by azhang on 10/10/2017.
 */
(function () {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .controller('attributesController', attributesController);

    attributesController.$inject = ['attributesService'];

    function attributesController(attributesService) {
        var self = this;
        self.attributes = [];
        self.newAttribute = {};
        self.newAttribute.attributeValues=[];
        self.selectedAttribute = null;
        self.dropdownValues=null;


        getAllAttibutes();

        /**
         * Public functions
         */
        this.saveNewAttribute = createNewAttribute;
        this.resetNewAttribute = resetNewAttribute;
        this.selectAttribute = selectAttribute;
        this.deleteAttribute = deleteAttribute;


        function createNewAttribute() {
            if(self.newAttribute.type=='dropdown' && self.dropdownValues!== null){
                self.dropdownValues.split(';').forEach(function (element) {
                    var attributeValue={};
                    attributeValue.value = element;
                    self.newAttribute.attributeValues.push(attributeValue);
                });
            }
            console.log("object: " + JSON.stringify(self.newAttribute));
            attributesService.createAttribute(self.newAttribute).then(
                function (response) {
                    resetNewAttribute();
                    getAllAttibutes();
                },
                function (errResponse) {
                    console.error('Error while creating attribute: ' + errResponse.data.message);
                }
            );
        };

        function resetNewAttribute() {

            self.newAttribute = {};
            self.newAttribute.attributeValues=[];
            self.dropdownValues=null;
        };

        function selectAttribute(attribute) {
            self.selectedAttribute = angular.copy(attribute);
        };

        function deleteAttribute() {
            attributesService.deleteAttribute(self.selectedAttribute).then(
                function (response) {
                    getAllAttibutes();
                },
                function (errResponse) {
                    console.error('Error while deleting : ' + errResponse.toString());
                }
            )
        };

        /**
         * Private functions
         */
        function getAllAttibutes() {
            attributesService.getAllAttributes().then(function (response) {
                    self.attributes = response.data;
                }, function (errResponse) {
                    console.error('Error while get all attributes : ' + errResponse.data.message);
                }
            )
        }
    }
})();
