var testTool = angular.module("testTool", ["ngRoute"]);

testTool.config(function($routeProvider) {

    $routeProvider.when('/', {
            templateUrl: 'html/main.html',
            controller: 'mainRoute'
        })

        .when('/test/:testId', {
            templateUrl: 'html/test.html',
            controller: 'testRoute'
        })

    })

testTool.controller('mainRoute', function($scope, $routeParams) {

});

testTool.controller('testRoute', function($scope, $routeParams) {

});

testTool.controller('mainCtrl', function($scope) {

    console.log("mainCtrl");

    $scope.test = "Example of how a string resolves";

});

testTool.controller('testCtrl', function($scope, $http, $routeParams) {

    console.log("testCtrl");

    $scope.test = $routeParams.testId;

    $scope.testFunction = function(){

        console.log("Example");

    }

    //    $http.get("/test/returnAllTests").then(function(response){
    //
    //        $scope.tests = response.data;
    //
    //    })
});