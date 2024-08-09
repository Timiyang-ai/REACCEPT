public static Collection<MapTile> getTilesCoverage(final BoundingBox pBB, final int pZoomLevel){
        final Set<MapTile> result = new HashSet<>();
        final int mapTileUpperBound = 1 << pZoomLevel;
        final Point lowerRight = getMapTileFromCoordinates(
                pBB.getLatSouth(), pBB.getLonEast(), pZoomLevel);
        final Point upperLeft = getMapTileFromCoordinates(
                pBB.getLatNorth(), pBB.getLonWest(), pZoomLevel);
        int width = lowerRight.x - upperLeft.x + 1; // handling the modulo
        if (width <= 0) {
            width += mapTileUpperBound;
        }
        int height = lowerRight.y - upperLeft.y + 1; // handling the modulo
        if (height <= 0) {
            height += mapTileUpperBound;
        }
        for (int i = 0 ; i < width ; i ++) {
            for (int j = 0 ; j < height ; j ++) {
                final int x = MyMath.mod(upperLeft.x + i, mapTileUpperBound);
                final int y = MyMath.mod(upperLeft.y + j, mapTileUpperBound);
                result.add(new MapTile(pZoomLevel, x, y));
            }
        }
        return result;
    }