private static SpatialRDD<Geometry> readToGeometryRDD(JavaSparkContext sc, String inputPath) {
        return readToGeometryRDDfromFile(sc, inputPath, true);
    }