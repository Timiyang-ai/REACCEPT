public static JavaRDD<Geometry> readToGeometryRDD(JavaSparkContext sc, String inputPath, final GeometryFactory geometryFactory)
    {
        return readShapefile(sc, inputPath, geometryFactory);
    }