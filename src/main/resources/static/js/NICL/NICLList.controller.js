/**
 * Created by azhang on 01/11/2017.
 */
/**
 * Created by azhang on 16/10/2017.
 */
(function () {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .controller('NICLListController', NICLListController);

    NICLListController.$inject = ['NICLService'];

    function NICLListController(NICLService) {

            var self = this;
            self.groups=[];
            self.NICLHeadList=[];
            self.selectedGroupID;
            self.seletedNICLContent=[];
            self.selectedNICLHead={};
            getAllGroups();
        /**
         * Public functions
         */
            this.getNICLHeadByGroupID = getNICLHeadByGroupID;
            this.getNICLContentByHead=getNICLContentByHead;
        /**
         * Public functions implementation
         */
        function getNICLHeadByGroupID(){

            NICLService.getNICLHeadByGroupID(self.selectedGroupID).then(function (response) {
                    self.NICLHeadList = response.data;
                    console.log('fetching NICLHeadList with gid:['+ self.selectedGroupID+ '] successfully!');

                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }
        function getNICLContentByHead(headItem){
            console.log('fetching NICL Content with hid:['+ headItem.id + ']');
            self.selectedNICLHead=headItem;
            NICLService.getNICLContentByHeadID(self.selectedNICLHead.id).then(function (response) {
                    self.seletedNICLContent = response.data;
                    console.log('fetching NICL Content with hid:['+ self.selectedNICLHead.id + '] successfully!');

                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }

        /**
         * Private functions
         */
        function getAllGroups() {
            NICLService.getGroups().then(function (response) {
                    self.groups = response.data;
                    console.log('fetching groups successfully!');

                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }

    }
})();
