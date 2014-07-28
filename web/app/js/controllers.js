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
            $http.get('../webresources/albums/' + collection).success(function(data, status, headers, config) {
                $scope.albums = data;
            });
        };
        
        $scope.removeAlbum = function(id)
        {
            var albumIndex = -1;
            for (var i = 0; i < $scope.albums.length; i++) {
                if ($scope.albums[i]._id === id) {
                    albumIndex = i;
                    break;
                }
            }
            
            $http.delete('../webresources/albums/delete/' + id).success(function(data, status, headers, config) {
                $scope.albums.splice(albumIndex, 1);
            }).error(function(data, status, headers, config) {
                alert(';__; there was an error');
            });
        };
        
//        $scope.search = function (row) {
//            return !!((row.title.indexOf($scope.query || '') !== -1 
//                    || row.artist.indexOf($scope.query || '') !== -1 
//                    || row.genre.indexOf($scope.query || '') !== -1 
//                    || row.label.indexOf($scope.query || '') !== -1 
//                    || row.tracks.indexOf($scope.query || '') !== -1 
//                    || row.tags.indexOf($scope.query || '') !== -1 
//                    || row.year.indexOf($scope.query || '') !== -1));
//        };
    }
]);

pdAlbumsCatalogControllers.controller('AlbumInsertController', ['$scope', '$http',
    function($scope, $http) {
        $scope.newAlbum = {
            title: null,
            artist: null,
            genre: null,
            label: null,
            collection: null,
            coverPath: null,
            year: new Date().getFullYear(),
            tags: [],
            tracks: []
        };
        
        $scope.coverImageURL = 'img/albumart.jpg';
        $scope.newTrack = '';
        
        $scope.artistsDataSource = pdAlbumsCatalogControllers.artistsDataSource;
        $scope.genresDataSource = pdAlbumsCatalogControllers.genresDataSource;
        $scope.labelsDataSource = pdAlbumsCatalogControllers.labelsDataSource;
        $scope.collectionAutocompleteDataSource = pdAlbumsCatalogControllers.collectionAutocompleteDataSource;
        
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
        
        $scope.removeTrack = function(index) {
            $scope.newAlbum.tracks.splice(index, 1);
        };
        
        $scope.collectionSelected = function(e) {
            var collection = e.sender.value();
            $scope.newAlbum.collection = collection;
        };
        
        $scope.insertAlbum = function() {
            $http.put('../webresources/albums/insert', $scope.newAlbum).success(function(data, status, headers, config){
                alert('Your album was saved.');
                $scope.newAlbum.title = null;
                $scope.newAlbum.artist = null;
                $scope.newAlbum.genre = null;
                $scope.newAlbum.label = null;
                $scope.newAlbum.collection = null;
                $scope.newAlbum.coverPath = null;
                $scope.newAlbum.year = new Date().getFullYear();
                $scope.newAlbum.tags = [];
                $scope.newAlbum.tracks = [];
            }).error(function(data, status, headers, config){
                alert(';__; there was an error');
            });
        };
        
        $scope.$watch('newAlbum.coverPath', function() {
            if ($scope.newAlbum.coverPath !== null) {
                $scope.coverImageURL = $scope.newAlbum.coverPath;
            }
            else {
                $scope.coverImageURL = 'img/albumart.jpg';
            }
        });
    }
]);

pdAlbumsCatalogControllers.controller('AlbumUpdateController',['$scope', '$routeParams', '$http',
    function($scope, $routeParams, $http) {
        $http.get('../webresources/albums/album/' + $routeParams.albumId).success(function(data, status, headers, config) {
            $scope.newAlbum = data;
            $scope.coverImageURL = $scope.newAlbum.coverPath;
        }).error(function(data, status, headers, config) {
            alert(';__; there was an error');
        });
        
        $scope.newTrack = '';
        
        $scope.artistsDataSource = pdAlbumsCatalogControllers.artistsDataSource;
        $scope.genresDataSource = pdAlbumsCatalogControllers.genresDataSource;
        $scope.labelsDataSource = pdAlbumsCatalogControllers.labelsDataSource;
        $scope.collectionAutocompleteDataSource = pdAlbumsCatalogControllers.collectionAutocompleteDataSource;
        
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
        
        $scope.removeTrack = function(index) {
            $scope.newAlbum.tracks.splice(index, 1);
        };
        
//        $scope.collectionSelected = function(e) {
//            var collection = e.sender.value();
//            $scope.newAlbum.collection = collection;
//        };
    }
]);

// Common objects

pdAlbumsCatalogControllers.collectionAutocompleteDataSource = {
    type: 'json',
    transport: {
        read: {
            url: '../webresources/collections'
        }
    }
};

pdAlbumsCatalogControllers.collectionsDataSource = {
    type: 'json',
    serverFiltering: true,
    transport: {
        read: {
            url: '../webresources/collections'
        }
    }
};

pdAlbumsCatalogControllers.artistsDataSource = {
    type: 'json',
    transport: {
        read: {
            url: '../webresources/artists'
        }
    }
};

pdAlbumsCatalogControllers.genresDataSource = {
    type: 'json',
    transport: {
        read: {
            url: '../webresources/genres'
        }
    }
};

pdAlbumsCatalogControllers.labelsDataSource = {
    type: 'json',
    transport: {
        read: {
            url: '../webresources/labels'
        }
    }
};