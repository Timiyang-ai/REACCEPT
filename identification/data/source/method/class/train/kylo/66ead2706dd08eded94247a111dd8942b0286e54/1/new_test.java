@Test
    public void toScript() throws Exception {
        // Build the request
        final TransformRequest request = new TransformRequest();
        request.setScript("sqlContext.range(1,10)");

        // Test converting request to script
        final TransformService service = new TransformService(TransformScript.class, Mockito.mock(SparkScriptEngine.class), Mockito.mock(SparkContextService.class),
                                                              Mockito.mock(JobTrackerService.class));

        final String expected = IOUtils.toString(getClass().getResourceAsStream("transform-service-script1.scala"), "UTF-8");
        Assert.assertEquals(expected, service.toScript(request));
    }