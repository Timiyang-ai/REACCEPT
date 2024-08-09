@Test
    @SuppressWarnings("unchecked")
    public void execute () throws Exception
    {
        // Mock SQL context and script engine
        DataFrame dataFrame = Mockito.mock(DataFrame.class);
        Mockito.when(dataFrame.collectAsList()).thenReturn(new ArrayList<Row>());

        SQLContext context = Mockito.mock(SQLContext.class);
        Mockito.when(context.sql(Mockito.anyString())).thenReturn(dataFrame);

        ScriptEngine engine = Mockito.mock(ScriptEngine.class);
        Mockito.when(engine.getSQLContext()).thenReturn(context);

        // Test executing a request
        TransformRequest request = new TransformRequest();
        request.setScript("sqlContext.range(1,10)");

        TransformService service = new TransformService(engine);
        service.startAsync();
        service.awaitRunning();

        String table = null;
        try {
            table = service.execute(request);
        }
        finally {
            service.stopAsync();
        }

        // Test eval arguments
        ArgumentCaptor<String> evalScript = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List> evalBindings = ArgumentCaptor.forClass(List.class);
        Mockito.verify(engine).eval(evalScript.capture(), evalBindings.capture());

        String expectedScript = IOUtils.toString(getClass().getResourceAsStream("transform-service"
                + "-script1.scala"), "UTF-8");
        Assert.assertEquals(expectedScript, evalScript.getValue());

        List<NamedParam> bindings = evalBindings.getValue();
        Assert.assertEquals(2, bindings.size());
        Assert.assertEquals("database", bindings.get(0).name());
        Assert.assertEquals("String", bindings.get(0).tpe());
        Assert.assertEquals("spark_shell_temp", bindings.get(0).value());
        Assert.assertEquals("tableName", bindings.get(1).name());
        Assert.assertEquals("String", bindings.get(1).tpe());
        Assert.assertEquals(table, bindings.get(1).value());
    }