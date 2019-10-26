# General overview

Purpose is to use Clojure, re-frame, shadow-cljs, mapbox-gl plus various map sources to build an app for combining multiple outdoors trail mapping resources and GPS / GPX creation & visualization from those resources.

- Implemented mapant.fi map overlay

## Basic setup guide for developers

`yarn install`

`yarn run shadow-cljs watch app`

Open browser at [http://localhost:8280](http://localhost:8280)

## Prioritized roadmap

- Map overlay controls
- GPX track uploading
- GPX track downloading
- GPX track making
- MLL map overlay
- GPS Tracking

## Done items

- Add attribution to mapant.fi guys
- Mapant.fi overlay
- Customized mapbox style

## Wishful thinking

- Stay on GPS tracking via separate app..
- Mapant.fi raster layer looks best in EPSG:3067 projection, mapbox does not support it by default but there may be ways to use the original 3067 projection
