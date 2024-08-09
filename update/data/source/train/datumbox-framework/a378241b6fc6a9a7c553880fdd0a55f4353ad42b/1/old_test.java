@Test
    public void testExtract() {
        logger.info("extract");
         
        DatabaseConfiguration dbConf = TestUtils.getDBConfig();
        
        String dbName = this.getClass().getSimpleName();
        
        String text = null;
        try {
            text = TestUtils.webRequest("http://www.example.org/");
        }
        catch(UncheckedIOException ex) {
            logger.warn("Unable to download datasets, skipping test.");
            throw new RuntimeException(ex);
        }
        
        CETR.Parameters parameters = new CETR.Parameters();
        parameters.setNumberOfClusters(2);
        parameters.setAlphaWindowSizeFor2DModel(3);
        parameters.setSmoothingAverageRadius(2);
        CETR instance = new CETR(dbName, dbConf);
        String expResult = "This domain is established to be used for illustrative examples in documents. You may use this domain in examples without prior coordination or asking for permission.";
        String result = instance.extract(text, parameters);
        assertEquals(expResult, result);
        instance=null;
    }