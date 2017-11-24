/**
 * Created by azhang on 22/11/2017.
 */
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
        .controller('NICLReviewFormController', NICLReviewFormController);

    NICLReviewFormController.$inject = ['NICLService','shareService'];

    function NICLReviewFormController(NICLService, shareService) {
        var self = this;
        self.selectedHID;

        self.templateVO={};
        self.NICLContentList=[];

        getTemplateValueObject();

        getNICLContentList();
        /**private function**/
        function getNICLHeader(){
            self.selectedHID = shareService.getSelectedHID();
        }
        function getTemplateValueObject(){

            getNICLHeader();
            NICLService.getTemplateByHID(self.selectedHID).then(function (response) {
                self.templateVO = response.data;
                NICLService.sortTemplateSectionAttributes(self.templateVO);
                console.log('templates get!');
                console.log('template content: ' + JSON.stringify(self.templateVO));
            }, function (errResponse) {
                console.error('Error while getting the template attributes with tid : ' + templateVO.id + ';' + errResponse.toString());
            })
        }
        function getNICLContentList(){
            NICLService.getNICLContentByHeadID(self.selectedHID).then(function (response) {
                self.NICLContentList = response.data;
                parserContentValueToTemplateVO();
                console.log('content list get!');
                console.log('content list: ' + JSON.stringify(self.NICLContentList));
            }, function (errResponse) {
                console.error('Error while getting content attributes with tid : ' + selectedHID + ';' + errResponse.toString());
            })
        }
        //put value into template section attribute
        function parserContentValueToTemplateVO(){

                self.NICLContentList.forEach(function (contentElement) {
                    self.templateVO.sections.forEach(function (section){
                        section.sectionAttributes.forEach(function(sectionAttribute){
                            if(sectionAttribute.attribute.id ==  contentElement.attribute.id){
                                sectionAttribute.attribute.inputValue = contentElement.value;
                            }
                        })
                    })
                })


        }


    }


})();
