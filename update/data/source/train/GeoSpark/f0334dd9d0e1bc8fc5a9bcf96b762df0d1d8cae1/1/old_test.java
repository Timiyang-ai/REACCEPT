@Test
    public void testReadToGeometryRDD()
            throws IOException
    {
        // load shape with geotool.shapefile
        String inputLocation = getShapeFilePath("polygon");
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = loadFeatures(inputLocation);
        // load shapes with our tool
        JavaRDD<Geometry> shapeRDD = ShapefileReader.readToGeometryRDD(sc, inputLocation);
        Assert.assertEquals(shapeRDD.collect().size(), collection.size());
    }