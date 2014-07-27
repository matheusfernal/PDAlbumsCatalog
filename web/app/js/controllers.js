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
    }
]);

pdAlbumsCatalogControllers.controller('AlbumInsertController', ['$scope', '$http',
    function($scope, $http) {
        $scope.newAlbum = {
            title: '',
            artist: '',
            genre: '',
            label: '',
            collection: '',
            year: new Date().getFullYear(),
            tags: [],
            tracks: []
        };
        
        $scope.artistsDataSource = {
            type: 'json',
            serverFiltering: true,
            transport: {
                read: {
                    url: '../webresources/artists'
                }
            }
        };
        
        $scope.genresDataSource = {
            type: 'json',
            serverFiltering: true,
            transport: {
                read: {
                    url: '../webresources/genres'
                }
            }
        };
        
        $scope.labelsDataSource = {
            type: 'json',
            serverFiltering: true,
            transport: {
                read: {
                    url: '../webresources/labels'
                }
            }
        };
    }
]);