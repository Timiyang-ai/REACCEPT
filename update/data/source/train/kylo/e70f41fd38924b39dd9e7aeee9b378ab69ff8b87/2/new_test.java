@Test
    @SuppressWarnings("unchecked")
    public void execute() throws Exception {
        // Mock Spark context service
        final SparkContextService sparkContextService = Mockito.mock(SparkContextService.class);

        // Mock Spark script engine
        final SparkScriptEngine engine = Mockito.mock(SparkScriptEngine.class);
        Mockito.when(engine.eval(Mockito.anyString(), Mockito.anyListOf(NamedParam.class))).thenReturn(new MockTransformResult());
        Mockito.when(engine.getSparkContext()).thenReturn(Mockito.mock(SparkContext.class));

        // Test executing a request
        final TransformRequest request = new TransformRequest();
        request.setScript("sqlContext.range(1,10)");

        final TransformService service = new TransformService(TransformScript.class, engine, sparkContextService, new MockTransformJobTracker());
        final TransformResponse response = service.execute(request);
        Assert.assertEquals(TransformResponse.Status.SUCCESS, response.getStatus());

        // Test eval arguments
        final ArgumentCaptor<String> evalScript = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<List> evalBindings = ArgumentCaptor.forClass(List.class);
        Mockito.verify(engine).eval(evalScript.capture(), evalBindings.capture());

        final String expectedScript = IOUtils.toString(getClass().getResourceAsStream("transform-service-script1.scala"), "UTF-8");
        Assert.assertEquals(expectedScript, evalScript.getValue());

        final List<NamedParam> bindings = evalBindings.getValue();
        Assert.assertEquals(3, bindings.size());
        Assert.assertEquals("profiler", bindings.get(0).name());
        Assert.assertEquals("com.thinkbiganalytics.spark.dataprofiler.Profiler", bindings.get(0).tpe());
        Assert.assertNull(bindings.get(0).value());
        Assert.assertEquals("sparkContextService", bindings.get(1).name());
        Assert.assertEquals("com.thinkbiganalytics.spark.SparkContextService", bindings.get(1).tpe());
        Assert.assertEquals(sparkContextService, bindings.get(1).value());
        Assert.assertEquals("tableName", bindings.get(2).name());
        Assert.assertEquals("String", bindings.get(2).tpe());
        Assert.assertTrue(((String) bindings.get(2).value()).matches("^[0-9a-f]{32}$"));
    }