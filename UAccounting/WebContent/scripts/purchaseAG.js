var purchase = angular
    .module("purchaseModule", [])
    .controller("purchaseController", function($scope, $http){
    	
    	var url = "http://localhost:8080/UAccounting/purchase?ch=purchaseBillShow";
    	$http.get(url).then( function(response) {
    		$scope.list = response.data;
         });
    });