'use strict';


// Declare app level module which depends on filters, and services
var pdAlbumsCatalogApp = angular.module('PDAlbumsCatalog', [
  'ngRoute',
  'PDAlbumsCatalog.filters',
  'PDAlbumsCatalog.services',
  'PDAlbumsCatalog.directives',
  'PDAlbumsCatalog.controllers'
]);

pdAlbumsCatalogApp.config(['$routeProvider', 
    function($routeProvider) {
        $routeProvider.
            when('/albums', {
                templateUrl: 'partials/albums-list.html',
                controller: 'AlbumsListController'
            }).
            otherwise({
                redirectTo: '/albums'
            });
    }]);
