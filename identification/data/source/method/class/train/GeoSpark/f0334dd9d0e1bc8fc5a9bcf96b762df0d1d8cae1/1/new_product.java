public static SpatialRDD<Geometry> readToGeometryRDD(JavaSparkContext sc, String inputPath, final GeometryFactory geometryFactory)
    {
        SpatialRDD<Geometry> spatialRDD = new SpatialRDD();
        spatialRDD.rawSpatialRDD = readShapefile(sc, inputPath, geometryFactory);
        try {
            spatialRDD.fieldNames = readFieldNames(sc, inputPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return spatialRDD;
    }