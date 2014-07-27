'use strict';

/* Controllers */

var pdAlbumsCatalogControllers = angular.module('PDAlbumsCatalog.controllers', []);


pdAlbumsCatalogControllers.controller('AlbumsListController', ['$scope', '$http',
    function($scope, $http) {
        $http.get('../webresources/albums').success(function(data) {
            $scope.albums = data;
        });
        
        $scope.collectionsDataSource = {
            type: 'json',
            serverFiltering: true,
            transport: {
                read: {
                    url: '../webresources/collections'
                }
            }
        };
        
        $scope.collectionSelected = function(e)
        {
            var collection = e.sender.value();
            $http.get('../webresources/albums/' + collection).success(function(data) {
                $scope.albums = data;
            });
        };
    }]);