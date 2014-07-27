'use strict';

/* Filters */

var pdAlbumsCatalogFilters = angular.module('PDAlbumsCatalog.filters', []);
  
pdAlbumsCatalogFilters.filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    };
  }]);
  
pdAlbumsCatalogFilters.filter('albumCoverSrc', function(){
   return function(album) {
       return album.hasOwnProperty('coverPath') ? album.coverPath : 'img/albumart.jpg';
   } ;
});
