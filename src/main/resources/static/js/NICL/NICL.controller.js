/**
 * Created by azhang on 16/10/2017.
 */
(function () {
    'use strict';
    var app= angular
        .module('checklistAutomationApp');
        app.directive("dynamicName",function($compile){
            return {
                restrict:"A",
                terminal:true,
                priority:1000,
                link:function(scope,element,attrs){
                    element.attr('name', scope.$eval(attrs.dynamicName));
                    element.removeAttr("dynamic-name");
                    $compile(element)(scope);
                }
            }
        });
        app.controller('NICLController', NICLController);

    NICLController.$inject = ['NICLService'];



    function NICLController(NICLService) {

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });

        var self = this;
        /* for render first page */
        self.groups = [];
        self.templates = [];
        self.selectedGroupID;
        self.selectedTemplateID;
        self.selectedTemplate = {};
        self.newNICLHead = {};
        /* for render second page */
        self.createdNICLHead={};
        self.disableFlag = false;
        self.showFormFlag = false;
        self.showResultFlag = false;
        /* for render third page */

        self.NICLContentList=[];
        self.NICLContentListWithoutValue=[];
        getAllGroups();

        this.getGroupTemplates = getGroupTemplates;
        this.getTemplateByID = getTemplateByID;
        this.showForm = showForm;
        this.clearAll = clearAll;
        this.backToForm = backToForm;
        this.submitContent=submitContent;
        this.resetForm=resetForm;
        this.showReviewForm=showReviewForm;


        self.inputValues=[];

        /**
         * Public functions
         */



        function clearAll(){

           NICLService.deleteNICLHeadByID(self.createdNICLHead.id).then(

                function (response) {
                    console.log('delete done with head id: ' + self.createdNICLHead.id);

                },
                function (errResponse) {
                    console.error('Error while deleting with head id: ' + self.createdNICLHead.id);
                }
            );

            clearCachedData();
        }

        function resetForm(){
            self.NICLContentList=angular.copy(self.NICLContentListWithoutValue);
        }


        function showReviewForm(){
            self.showResultFlag = true;
            self.showFormFlag = false;

        }

        function submitContent(){
            NICLService.createNICLContentSet(self.NICLContentList).then(

                function (response) {
                    clearCachedData();
                    getAllGroups();
                },
                function (errResponse) {
                    console.error('Error while creating NIClContentSet: ' + errResponse.data.message);
                }
            );
        }
        function backToForm() {
            self.showResultFlag = false;
            self.showFormFlag = true;

        }

        function showForm() {

            getNewNICLHeadInfo();
            createNewNIClHead();
            self.showFormFlag = true;
            self.disableFlag = true;

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
                self.NICLContentListWithoutValue=angular.copy(self.NICLContentList);
                self.inputValues=angular.copy(self.NICLContentList);
                console.log("content list with value:" + headID);
                console.log("fetching NICLContent without value assigned done");
                console.log('NICLContentListWithoutValue: ' + JSON.stringify(self.NICLContentList));

            }, function (errResponse) {
                console.error('Error while fetching NICLContent without value assigned done : ' + self.createdNICLHead.id + ';' + errResponse.toString());
            })

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
        function getAllGroups() {
            NICLService.getGroups().then(function (response) {
                    self.groups = response.data;
                    console.log(self.groups);

                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }
        function clearCachedData(){
            self.templates = [];
            self.selectedGroupID = null;
            self.selectedTemplateID=null;
            self.selectedTemplate = {};
            self.newNICLHead = {};
            self.disableFlag = false;
            self.showFormFlag = false;
            self.showResultFlag = false;
            self.NICLContentList=[];
            self.NICLContentListWithoutValue=[];
            self.createdNICLHead={};
        }


    }
})();
