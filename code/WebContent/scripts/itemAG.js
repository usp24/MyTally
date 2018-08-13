var item = angular
    .module("itemModule", [])
    .controller("itemController", function($scope, $http){
    	
    	var url = "http://localhost:8080/UAccounting/item?ch=itemView";
    	$http.get(url).then( function(response) {
    		$scope.list = response.data;
         });
    });
