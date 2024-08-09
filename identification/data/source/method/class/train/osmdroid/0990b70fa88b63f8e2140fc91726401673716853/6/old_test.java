    private static Collection<Long> getTilesCoverage(final BoundingBox pBB, final int pZoomLevel){
        final Set<Long> result = new LinkedHashSet<>();
        final int mapTileUpperBound = 1 << pZoomLevel;
        final Rect rect = CacheManager.getTilesRect(pBB, pZoomLevel);
        for (int j = rect.top ; j <= rect.bottom ; j ++) {
            for (int i = rect.left ; i <= rect.right ; i ++) { // x incrementing first for the test
                final int x = MyMath.mod(i, mapTileUpperBound);
                final int y = MyMath.mod(j, mapTileUpperBound);
                result.add(MapTileIndex.getTileIndex(pZoomLevel, x, y));
            }
        }
        return result;
    }