@Test
    public void testReproject() throws NoSuchAuthorityCodeException, FactoryException {
        
        // do it again, make sure the image does not turn black since 
        GridCoverage2D coverage_ = project(ushortCoverage,CRS.parseWKT(GOOGLE_MERCATOR_WKT), null,"nearest", null);
        
        // reproject the ushort and check that things did not go bad, that is it turned black
        coverage_=(GridCoverage2D) Operations.DEFAULT.extrema(coverage_);
        Object minimums = coverage_.getProperty(Extrema.GT_SYNTHETIC_PROPERTY_MINIMUM);
        Assert.assertTrue(minimums instanceof double[]) ;
        final double[] mins=(double[]) minimums;
        Object maximums = coverage_.getProperty(Extrema.GT_SYNTHETIC_PROPERTY_MAXIMUM);
        Assert.assertTrue(maximums instanceof double[]) ;
        final double[] max=(double[]) maximums;        
        boolean fail=true;
        for(int i=0;i<mins.length;i++)
            if(mins[i]!=max[i]&&max[i]>0)
                fail=false;
        Assert.assertFalse("Reprojection failed", fail);
        
        //exception in case the target crs does not comply with the target gg crs
        try{
            // we supplied both crs and target gg in different crs, we get an exception backS
            assertEquals("Warp", showProjected(coverage,CRS.parseWKT(GOOGLE_MERCATOR_WKT), coverage.getGridGeometry(), null));
            Assert.assertTrue("We should not be allowed to set different crs for target crs and target gg", false);
        }catch (Exception e) {
            // ok!
        }
        
        
    }