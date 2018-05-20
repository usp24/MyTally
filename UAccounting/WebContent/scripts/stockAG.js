var stock = angular
    .module("stockModule", [])
    .controller("stockController", function($scope, $http){
    	
    	var url = "http://localhost:8080/UAccounting/item?ch=itemStock";
    	$http.get(url).then( function(response) {
    		$scope.list = response.data;
         });
    });
