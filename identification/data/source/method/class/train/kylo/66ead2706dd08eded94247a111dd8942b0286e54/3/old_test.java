@Test
    @SuppressWarnings("unchecked")
    public void execute() throws Exception {
        // Mock data set
        final DataSet dataSet = Mockito.mock(DataSet.class);
        Mockito.when(dataSet.persist(Mockito.any(StorageLevel.class))).thenReturn(dataSet);
        Mockito.when(dataSet.schema()).thenReturn(new StructType());

        // Mock Spark context service
        final SparkContextService sparkContextService = Mockito.mock(SparkContextService.class);

        // Mock Spark script engine
        final SparkScriptEngine engine = Mockito.mock(SparkScriptEngine.class);
        Mockito.when(engine.eval(Mockito.anyString(), Mockito.anyListOf(NamedParam.class))).thenReturn(dataSet);
        Mockito.when(engine.getSparkContext()).thenReturn(Mockito.mock(SparkContext.class));

        // Test executing a request
        final TransformRequest request = new TransformRequest();
        request.setScript("sqlContext.range(1,10)");

        final TransformService service = new TransformService(TransformScript.class, engine, sparkContextService, new MockTransformJobTracker());
        final TransformResponse response = service.execute(request);
        Assert.assertEquals(TransformResponse.Status.PENDING, response.getStatus());

        // Test eval arguments
        final ArgumentCaptor<String> evalScript = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<List> evalBindings = ArgumentCaptor.forClass(List.class);
        Mockito.verify(engine).eval(evalScript.capture(), evalBindings.capture());

        String expectedScript = null;
        try (InputStream stream = getClass().getResourceAsStream("transform-service-script1.scala")) {
            expectedScript = IOUtils.toString(stream, "UTF-8");
        }

        if (expectedScript == null) {
            throw new Exception("transform-service-script1.scala failed to load");
        }

        Assert.assertEquals(expectedScript, evalScript.getValue());

        final List<NamedParam> bindings = evalBindings.getValue();
        Assert.assertEquals(1, bindings.size());

        Assert.assertEquals("sparkContextService", bindings.get(0).name());
        Assert.assertEquals("com.thinkbiganalytics.spark.SparkContextService", bindings.get(0).tpe());
        Assert.assertEquals(sparkContextService, bindings.get(0).value());
    }