public static SpatialRDD<Geometry> readToGeometryRDD(JavaSparkContext sc, String inputPath) {
        return readToGeometryRDD(sc, inputPath, true, false);
    }