public static List<Long> getTilesCoverage(final ArrayList<GeoPoint> pGeoPoints,
                                              final int pZoomMin, final int pZoomMax) {
        final List<Long> result = new ArrayList<>();
        for (int zoomLevel = pZoomMin; zoomLevel <= pZoomMax; zoomLevel++) {
            final Collection<Long> resultForZoom = getTilesCoverage(pGeoPoints, zoomLevel);
            result.addAll(resultForZoom);
        }
        return result;
    }