'use strict';

/* Controllers */

var pdAlbumsCatalogControllers = angular.module('PDAlbumsCatalog.controllers', []);


pdAlbumsCatalogControllers.controller('AlbumsListController', ['$scope', '$http',
    function($scope, $http) {
        $http.get('../webresources/albums').success(function(data) {
            $scope.albums = data;
        });
    }]);