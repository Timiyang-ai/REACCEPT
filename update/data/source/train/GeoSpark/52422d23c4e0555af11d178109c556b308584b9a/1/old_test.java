@Test
    public void testReadToGeometryRDD()
            throws IOException
    {
        // would crash with java.lang.IllegalArgumentException: Points of LinearRing do not form a closed linestring if Invalid syntax is not skipped
        SpatialRDD geojsonRDD = GeometryReader.readToGeometryRDD(sc, invalidSyntaxGeoJsonGeomWithFeatureProperty,
                FileDataSplitter.GEOJSON, false, true);
        assertEquals(geojsonRDD.rawSpatialRDD.count(), 1);
    }