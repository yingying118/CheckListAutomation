(function () {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .controller('groupsController', groupsController);

    groupsController.$inject = ['groupsService'];

    function groupsController(groupsService) {
        var self = this;
        self.groups = [];
        self.newGroup = {};
        self.selectedGroup = null;

        getAllGroups();

        /**
         * Public functions
         */
        this.saveNewGroup = createNewGroup;
        this.resetNewGroup = resetNewGroup;
        this.selectGroup = selectGroup;
        this.deleteGroup = deleteGroup;


        function createNewGroup() {
            console.log(self.newGroup);
            groupsService.createGroup(self.newGroup).then(
                function (response) {
                    resetNewGroup();
                    getAllGroups();
                },
                function (errResponse) {
                    console.error('Error while creating group: ' + errResponse.data.message);
                }
            );
        };

        function resetNewGroup() {
            self.newGroup = {};
        }

        function selectGroup(group) {
            self.selectedGroup = angular.copy(group);
        }

        function deleteGroup() {
            groupsService.deleteGroup(self.selectedGroup).then(
                function (response) {
                    getAllGroups();
                },
                function (errResponse) {
                    console.error('Error while deleting : ' + errResponse.toString());
                }
            )
        };

        /**
         * Private functions
         */


        function getAllGroups() {
            groupsService.getGroups().then(function (response) {
                    self.groups = response.data;
                }, function (errResponse) {
                    console.error('Error while get all groups : ' + errResponse.toString());
                }
            )
        }
    }
})();
