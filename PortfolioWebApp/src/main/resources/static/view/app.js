var app = angular.module('app',[], function($locationProvider) {
	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});
});

app.service('PortfolioService', [ '$http', function($http) {
 
    this.getPortfolio = function getPortfolio(portfolioId) {
        return $http({
            method : 'GET',
            url : 'application/getportfolio/' + portfolioId
        });
    }
    
    this.getTweetsByUserName = function getTweetsByUserName(portfolioId) {
        return $http({
            method : 'GET',
            url : 'application/gettweets/' + portfolioId
        });
    }
    
    this.updatePortfolio = function updatePortfolio(portfolioId, portfolioData) {
        return $http({
            method : 'POST',
            data : angular.toJson(portfolioData),
            url : 'application/updateportfolio/' + portfolioId
        });
    }
    
} ]);

app.controller('PortfolioCtrl', ['$scope', 'PortfolioService', '$location',
	  function ($scope, PortfolioService, $location) {
	
	      $scope.getPortfolio = function () {
	          var id = $location.search().idportfolio;
	          PortfolioService.getPortfolio(id)
	            .then(function success(response) {
	                $scope.portfolio = response.data;
	                $scope.portfolio.id = id;
	                
	                PortfolioService.getTweetsByUserName(id)
		  	  	      .then(function success(response) {
		  	  	    	  $scope.tweets = response.data;
		  	  	      },
		  	  	      function error (response) {
		  	  	          $scope.message='';
		  	  	          $scope.errorMessage = 'Error getting tweets!';
		  	  	      });
	                
	                
	            },
	            function error (response) {
	                $scope.message = '';
	                if (response.status === 404){
	                    $scope.errorMessage = 'Portfolio not found!';
	                }
	                else {
	                    $scope.errorMessage = "Error getting portfolio!";
	                }
	            });
	      };
	      
	      $scope.updatePortfolio = function(){
	    	  var id = $scope.portfolio.id;
	    	  PortfolioService.updatePortfolio(id, $scope.portfolio)
	    	  .then(function success(response) {
	    		  $scope.message = 'Updated';
	    	  },
	            function error (response) {
	                $scope.message = '';
	                if (response.status === 404){
	                    $scope.errorMessage = 'Portfolio not found!';
	                }
	                else {
	                    $scope.errorMessage = "Error getting portfolio!";
	                }
	            });
	      };
	}
	
]);
