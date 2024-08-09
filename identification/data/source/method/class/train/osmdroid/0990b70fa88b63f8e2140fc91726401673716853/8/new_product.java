public static List<Long> getTilesCoverage(final BoundingBox pBB,
                                                 final int pZoomMin, final int pZoomMax) {
        final List<Long> result = new ArrayList<>();
        for (int zoomLevel = pZoomMin; zoomLevel <= pZoomMax; zoomLevel++) {
            final Collection<Long> resultForZoom = getTilesCoverage(pBB, zoomLevel);
            result.addAll(resultForZoom);
        }
        return result;
    }