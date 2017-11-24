/**
 * Created by azhang on 06/10/2017.
 */
(function() {
    'use strict';

    angular
        .module('checklistAutomationApp')
        .factory('shareService', shareService);


    function shareService() {
        var selectedHID;
        var services = {
            getSelectedHID: getSelectedHID,
            setSelectedHID: setSelectedHID,
        };

        return services;

        function getSelectedHID() {
            return selectedHID;
        }
        function setSelectedHID(hid){
            selectedHID=hid;
        }

    }
})();