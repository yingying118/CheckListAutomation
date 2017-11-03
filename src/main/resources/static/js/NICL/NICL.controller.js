/**
 * Created by azhang on 16/10/2017.
 */
(function () {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .controller('NICLController', NICLController);

    NICLController.$inject = ['NICLService'];

    function NICLController(NICLService) {


        var self = this;
        self.groups = [];
        self.templates = [];
        self.selectedGroupID;
        self.selectedTemplateID;
        self.selectedTemplate = {};

        self.newNICLHead = {};
        self.createdNICLHead={};
        self.checkAttributes = [];
        self.disableFlag = false;
        self.showFormFlag = false;
        self.showResultFlag = false;
        self.newAttributeList = {};
        self.NICLContentList=[];
        getAllGroups();

        this.getGroupTemplates = getGroupTemplates;
        this.getTemplateAttributes = getTemplateAttributes;
        this.getTemplateByID = getTemplateByID;
        this.getNewNICLHeadInfo = getNewNICLHeadInfo;
        this.showForm = showForm;
        this.reset = reset;
        this.createNewNIClHead = createNewNIClHead;
        this.printList = printList;
        this.goBack = goBack;
        this.backToForm = backToForm;
        this.submitContent=submitContent;



        /**
         * Public functions
         */

        function printList() {
            self.showResultFlag = true;
            self.showFormFlag = false;

          }

        function submitContent(){
            NICLService.createNICLContentSet(self.NICLContentList).then(

                function (response) {
                    goBack();
                },
                function (errResponse) {
                    console.error('Error while creating NIClContentSet: ' + errResponse.data.message);
                }
            );
        }
        function backToForm() {
            self.showResultFlag = false;
            self.showFormFlag = true;
            self.NICLContentList = [];

        }

        function showForm() {

            getNewNICLHeadInfo();
            createNewNIClHead();
            self.showFormFlag = true;
            self.disableFlag = true;

        }

        function reset() {
            self.NICLContentList = [];

        }

        function goBack() {
            self.selectedGroupID = null;
            self.newNICLHead = {};
            self.createdNICLHead={}
            self.templates = [];
            self.selectedTemplateID = null;
            self.selectedTemplate = {};
            self.showFormFlag = false;
            self.showResultFlag = false;
            self.disableFlag = false;
            self.checkAttributes = [];
            self.NICLContentList = [];

        }

        function getNewNICLHeadInfo() {
            self.newNICLHead.template = self.selectedTemplate;
        }


        function createNewNIClHead() {

            NICLService.createNICLHead(self.newNICLHead).then(
                function (response) {
                    self.createdNICLHead=response.data;
                    console.log('createdNICLHead with hid: ' + self.createdNICLHead.id );

                    console.log('new NICLHead created!' );
                    getNICLContentWithoutValueByHeadID(self.createdNICLHead.id );
                },
                function (errResponse) {
                    console.error('Error while creating NICLHead: ' + errResponse.data.message);
                }
            );
        }

        function getTemplateByID() {
            console.log("template id:" + self.selectedTemplateID);
            NICLService.getTemplateByID(self.selectedTemplateID).then(function (response) {
                self.selectedTemplate = response.data;
                console.log('templates get!');
            }, function (errResponse) {
                console.error('Error while getting the template attributes with tid : ' + selectedTemplate.id + ';' + errResponse.toString());
            })

        }

        function getTemplateAttributes() {

            NICLService.getTemplateAttributes(self.selectedTemplateID).then(function (response) {
                self.checkAttributes = response.data;
                console.log('templates attributes with tid: ' + self.selectedTemplateID + 'got');
            }, function (errResponse) {
                console.error('Error while getting the template attributes with tid : ' + self.selectedTemplateID + ';' + errResponse.toString());
            })

        }


        function getGroupTemplates() {
            console.log("fetching group templates");
            NICLService.getGroupTemplates(self.selectedGroupID).then(function (response) {
                self.templates = response.data;
                console.log("fetching group templates done");

            }, function (errResponse) {
                console.error('Error while getting the group templates with gid : ' + selectedGroupID + ';' + errResponse.toString());
            })
        }

        /**
         * Private functions
         */


        function getNICLContentWithoutValueByHeadID(headID){
            console.log("fetching NICLContent Without Value" + headID);
            NICLService.getNICLContentWithoutValueByHeadID(headID).then(function (response) {
                self.NICLContentList = response.data;
                console.log("content list with value:" + headID);
                console.log("fetching NICLContent without value assigned done");
                console.log('NICLContentListWithoutValue: ' + JSON.stringify(self.NICLContentList));

            }, function (errResponse) {
                console.error('Error while fetching NICLContent without value assigned done : ' + self.createdNICLHead.id + ';' + errResponse.toString());
            })

        }

        function getAllGroups() {
            NICLService.getGroups().then(function (response) {
                    self.groups = response.data;
                    console.log(self.groups);

                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }

    }
})();
