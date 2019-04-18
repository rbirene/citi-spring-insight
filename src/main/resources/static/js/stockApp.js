//create module, pass in ngRoute dependency
var stockApp = angular.module("stockApp", ["ngRoute", "chart.js"]);

//configure routes in app
stockApp.config(function($routeProvider){
  $routeProvider
  //homepage
  .when("/", {
    templateUrl: "html/main.html",
    controller: "mainRoute"
  })
  //days
  .when("/day/:dayName", {
    templateUrl: "html/day.html",
    controller: "dayRoute"
  })
})

//add controller for each view
stockApp.controller("mainRoute", function($scope, $routeParams){

});

stockApp.controller("dayRoute", function($scope, $routeParams){

});

//add controller for each view
stockApp.controller("mainCtrl", function($scope){
  console.log("mainCtrl");

  $scope.labels = ['1', '2', '3', '4', '5'];
  $scope.data = [65, 59, 80, 81, 56, 55, 40];
  $scope.series= ["series a", "series b"];
});

stockApp.controller("dayCtrl", function($scope, $http, $routeParams){
  console.log("dayCtrl");

  //$scope.labels = ['1', '2', '3', '4', '5'];
  //$scope.data = [65, 59, 80, 81, 56, 55, 40];
  //$scope.series= ["series a", "series b"];

  //best stocks
  $http.get("/daily/returnStocksByDay/" + $routeParams.dayName + "/true").then(function(response){

    $scope.bestStocks = response.data;
    console.log($scope.bestStocks);

    $scope.bestLabels = [$scope.bestStocks[0].ticker, $scope.bestStocks[1].ticker, $scope.bestStocks[2].ticker,
      $scope.bestStocks[3].ticker, $scope.bestStocks[4].ticker];

    $scope.bestData = [$scope.bestStocks[0].absoluteDifference, $scope.bestStocks[1].absoluteDifference,
        $scope.bestStocks[2].absoluteDifference,
        $scope.bestStocks[3].absoluteDifference, $scope.bestStocks[4].absoluteDifference];

    $scope.series= ["series a", "series b"];

  })

  //worst stocks
  $http.get("/daily/returnStocksByDay/" + $routeParams.dayName + "/false").then(function(response){

    $scope.worstStocks = response.data;
    console.log($scope.worstStocks);

    $scope.worstLabels = [$scope.worstStocks[0].ticker, $scope.worstStocks[1].ticker,
      $scope.worstStocks[2].ticker,
      $scope.worstStocks[3].ticker, $scope.worstStocks[4].ticker];

    $scope.worstData = [$scope.worstStocks[0].absoluteDifference, $scope.worstStocks[1].absoluteDifference, $scope.worstStocks[2].absoluteDifference,
        $scope.worstStocks[3].absoluteDifference, $scope.worstStocks[4].absoluteDifference];

    $scope.series= ["series a", "series b"];

  })
});

//chart
angular.module('myModule', []);
angular.module("app", ["chart.js"]).controller("barCtrl", function ($scope) {

});