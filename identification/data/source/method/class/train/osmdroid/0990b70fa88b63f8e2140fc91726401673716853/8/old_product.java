public static List<MapTile> getTilesCoverage(final BoundingBox pBB,
                                                 final int pZoomMin, final int pZoomMax) {
        final List<MapTile> result = new ArrayList<>();
        for (int zoomLevel = pZoomMin; zoomLevel <= pZoomMax; zoomLevel++) {
            final Collection<MapTile> resultForZoom = getTilesCoverage(pBB, zoomLevel);
            result.addAll(resultForZoom);
        }
        return result;
    }