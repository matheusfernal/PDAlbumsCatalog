'use strict';


// Declare app level module which depends on filters, and services
var pdAlbumsCatalogApp = angular.module('PDAlbumsCatalog', [
  'ngRoute',
  'kendo.directives',
  'PDAlbumsCatalog.filters',
  'PDAlbumsCatalog.services',
  'PDAlbumsCatalog.directives',
  'PDAlbumsCatalog.controllers',
  //'PDAlbumsCatalog.animations'
]);

pdAlbumsCatalogApp.config(['$routeProvider', 
    function($routeProvider) {
        $routeProvider.
            when('/albums', {
                templateUrl: 'partials/albums-list.html',
                controller: 'AlbumsListController'
            }).
            when('/albums/insert', {
                templateUrl: 'partials/album-insert.html',
                controller: 'AlbumInsertController'
            }).
            when('/albums/album/:albumId', {
                templateUrl: 'partials/album-insert.html',
                controller: 'AlbumUpdateController'
            }).
            otherwise({
                redirectTo: '/albums'
            });
    }]);
