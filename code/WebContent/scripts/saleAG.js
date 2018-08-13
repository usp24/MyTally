var sale = angular
    .module("saleModule", [])
    .controller("saleController", function($scope, $http){
    	
    	var url = "http://localhost:8080/UAccounting/sales?ch=salesBillShow";
    	$http.get(url).then( function(response) {
    		$scope.list = response.data;
         });
    });