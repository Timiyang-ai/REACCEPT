@Test
    public void toScript() throws Exception {
        // Build the request
        final TransformRequest request = new TransformRequest();
        request.setScript("sqlContext.range(1,10)");

        // Test converting request to script
        final TransformService service = new TransformService(TransformScript.class, Mockito.mock(SparkScriptEngine.class), Mockito.mock(SparkContextService.class),
                                                              Mockito.mock(JobTrackerService.class), Mockito.mock(DataSetConverterService.class),Mockito.mock(KyloCatalogClientBuilder.class));

        InputStream inputStream = getClass().getResourceAsStream("transform-service-script1.scala");
        final String expected = IOUtils.toString(inputStream, "UTF-8");
        inputStream.close();
        Assert.assertEquals(expected, service.toScript(request));
    }