var supplier = angular
    .module("supplierModule", [])
    .controller("supplierController", function($scope, $http){
    	
    	var url = "http://localhost:8080/UAccounting/supplier?ch=supplierView";
    	$http.get(url).then( function(response) {
    		$scope.list = response.data;
         });
    });
