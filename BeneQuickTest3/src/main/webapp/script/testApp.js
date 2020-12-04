
angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {
             $scope.members=[];
            fetchData();
            function fetchData() {
                $http({method: 'GET', url: 'api/load-data'}).then(function (response) {

                    $scope.members.push(response.data);
                     console.log($scope.members);
                }, function (reason) {
                    console.log('error ' + reason)
                });
            }

        }]);
