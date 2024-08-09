public static double tileXToLongitudeWithScaleFactor(long tileX, double scaleFactor) {
        return pixelXToLongitudeWithScaleFactor(tileX * DUMMY_TILE_SIZE, scaleFactor, DUMMY_TILE_SIZE);
    }