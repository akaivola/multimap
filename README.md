# General overview

Purpose is to use Clojure, re-frame, shadow-cljs, mapbox-gl plus various map sources to build an app for combining multiple outdoors trail mapping resources and GPS / GPX creation & visualization from those resources.

- Implemented mapant.fi map overlay

## Basic setup guide for developers

`yarn install`

`yarn run shadow-cljs watch app`

Open browser at [http://localhost:8280](http://localhost:8280)

## Rudimentary unprioritized roadmap

- Add attribution to mapant.fi guys
- Mapant.fi raster layer looks best in EPSG:3067 projection, mapbox does not support it by default but there may be ways to use the original 3067 projection
- MLL map overlay
- GPS Tracking
- Map overlay controls
- GPX track uploading
- GPX track downloading
- GPX track making

## Done items

- Mapant.fi overlay

## Wishful thinking

- Stay on GPS tracking via separate app..

