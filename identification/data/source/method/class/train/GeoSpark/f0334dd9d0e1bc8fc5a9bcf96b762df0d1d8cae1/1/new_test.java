@Test
    public void testReadToGeometryRDD()
            throws IOException
    {
        // load shape with geotool.shapefile
        String inputLocation = getShapeFilePath("polygon");
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = loadFeatures(inputLocation);
        // load shapes with our tool
        SpatialRDD shapeRDD = ShapefileReader.readToGeometryRDD(sc, inputLocation);
        assertEquals(shapeRDD.rawSpatialRDD.collect().size(), collection.size());
    }