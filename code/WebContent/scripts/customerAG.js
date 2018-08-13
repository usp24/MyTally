var customer = angular
    .module("customerModule", [])
    .controller("customerController", function($scope, $http){
    	
    	var url = "http://localhost:8080/UAccounting/customer?ch=customerView";
    	$http.get(url).then( function(response) {
    		$scope.list = response.data;
         });
    });