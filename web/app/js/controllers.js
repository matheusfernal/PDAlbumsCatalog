'use strict';

/* Controllers */

var pdAlbumsCatalogControllers = angular.module('PDAlbumsCatalog.controllers', []);

pdAlbumsCatalogControllers.controller('AlbumsListController', ['$scope', '$http',
    function($scope, $http) {
        $http.get('../webresources/albums').success(function(data, status, headers, config) {
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
            coverPath: '',
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
        
        $scope.coverImageURL = 'img/albumart.jpg';
        $scope.newTrack = '';
        
        $scope.addNewTrack = function() {
            if ($scope.newTrack !== '') {
                $scope.newAlbum.tracks.push({ name: $scope.newTrack, number: $scope.newAlbum.tracks.length + 1 });
                $scope.newTrack = '';
            }
        };
        
        $scope.addNewTrackEnter = function(e) {
            if (e.keyCode === 13) {
                $scope.addNewTrack();
            }  
        };
        
        $scope.trackReordered = function(e) {

        };
        
        $scope.removeTrack = function(index)
        {
            $scope.newAlbum.tracks.splice(index, 1);
        };
        
        $scope.$watch('newAlbum.coverPath', function() {
            if ($scope.newAlbum.coverPath !== '') {
                $scope.coverImageURL = $scope.newAlbum.coverPath;
            }
            else {
                $scope.coverImageURL = 'img/albumart.jpg';
            }
        });
        
        $scope.insertAlbum = function() {
            $http.put('../webresources/albums/insert', $scope.newAlbum).success(function(data, status, headers, config){
                alert('sucesso');
            }).error(function(data, status, headers, config){
                alert('erro');
            });
        };
    }
]);