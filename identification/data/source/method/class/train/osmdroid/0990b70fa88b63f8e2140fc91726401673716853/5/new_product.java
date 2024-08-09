public static Collection<Long> getTilesCoverage(final BoundingBox pBB, final int pZoomLevel){
        final Set<Long> result = new LinkedHashSet<>();
        for (Long mapTile : getTilesCoverageIterable(pBB, pZoomLevel, pZoomLevel)) {
            result.add(mapTile);
        }
        return result;
    }