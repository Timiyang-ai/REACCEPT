@Test
    @SuppressWarnings("unchecked")
    public void execute() throws Exception {
        SQLContext context = Mockito.mock(SQLContext.class);
        SparkScriptEngine engine = Mockito.mock(SparkScriptEngine.class);
        Mockito.when(engine.eval(Mockito.anyString(), Mockito.any(List.class))).thenReturn(new Callable<TransformResponse>() {
            @Override
            public TransformResponse call() throws Exception {
                TransformResponse response = new TransformResponse();
                response.setStatus(TransformResponse.Status.SUCCESS);
                return response;
            }
        });
        Mockito.when(engine.getSparkContext()).thenReturn(Mockito.mock(SparkContext.class));
        Mockito.when(engine.getSQLContext()).thenReturn(context);

        // Test executing a request
        TransformRequest request = new TransformRequest();
        request.setScript("sqlContext.range(1,10)");

        TransformJobTracker tracker = new TransformJobTracker() {

            @Override
            public void addSparkListener(@Nonnull SparkScriptEngine engine) {

            }
        };
        TransformService service = new TransformService(engine, kerberosTicketConfiguration, tracker) {
            @Override
            void createDatabaseWithoutKerberos() {
                //do nothing such that we don't need to mock out context.sql(...) methods
                //which would require mocking either DataFrame or Dataset for different versions of Spark
            }
        };
        service.startAsync();
        service.awaitRunning();

        final TransformResponse response;
        try {
            response = service.execute(request);
        } finally {
            service.stopAsync();
        }

        Assert.assertEquals(TransformResponse.Status.SUCCESS, response.getStatus());

        // Test eval arguments
        ArgumentCaptor<String> evalScript = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List> evalBindings = ArgumentCaptor.forClass(List.class);
        Mockito.verify(engine).eval(evalScript.capture(), evalBindings.capture());

        String expectedScript = IOUtils.toString(getClass().getResourceAsStream("transform-service-script1.scala"), "UTF-8");
        Assert.assertEquals(expectedScript, evalScript.getValue());

        List<NamedParam> bindings = evalBindings.getValue();
        Assert.assertEquals(2, bindings.size());
        Assert.assertEquals("database", bindings.get(0).name());
        Assert.assertEquals("String", bindings.get(0).tpe());
        Assert.assertEquals("spark_shell_temp", bindings.get(0).value());
        Assert.assertEquals("tableName", bindings.get(1).name());
        Assert.assertEquals("String", bindings.get(1).tpe());
        Assert.assertTrue(((String) bindings.get(1).value()).matches("^[0-9a-f]{32}$"));
    }