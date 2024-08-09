@Test
    public void toScript() throws Exception {
        // Mock the script engine
        SparkScriptEngine engine = Mockito.mock(SparkScriptEngine.class);
        Mockito.when(engine.getSparkContext()).thenReturn(Mockito.mock(SparkContext.class));

        // Build the request
        TransformRequest request = new TransformRequest();
        request.setScript("sqlContext.range(1,10)");

        // Test converting request to script
        String expected = IOUtils.toString(getClass().getResourceAsStream("transform-service-script1.scala"), "UTF-8");

        TransformJobTracker tracker = Mockito.mock(TransformJobTracker.class);
        TransformService service = new TransformService(engine, kerberosTicketConfiguration, tracker);
        Assert.assertEquals(expected, service.toScript(request));
    }