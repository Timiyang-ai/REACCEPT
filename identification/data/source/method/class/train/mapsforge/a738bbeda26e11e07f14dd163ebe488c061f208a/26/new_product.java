public static int longitudeToTileXWithScaleFactor(double longitude, double scaleFactor) {
        return pixelXToTileXWithScaleFactor(longitudeToPixelXWithScaleFactor(longitude, scaleFactor, DUMMY_TILE_SIZE), scaleFactor, DUMMY_TILE_SIZE);
    }