@Test
    public void create() throws Exception {
        // Mock transform objects
        TransformRequest transformRequest = new TransformRequest();
        transformRequest.setScript("sqlContext.sql(\"SELECT * FROM invalid\")");

        TransformResponse transformResponse = new TransformResponse();
        transformResponse.setProgress(0.0);
        transformResponse.setStatus(TransformResponse.Status.PENDING);
        transformResponse.setTable("results");

        TransformService transformService = Mockito.mock(TransformService.class);
        Mockito.when(transformService.execute(transformRequest)).thenReturn(transformResponse);

        // Test transforming
        SparkShellTransformController controller = new SparkShellTransformController();
        controller.idleMonitorService = Mockito.mock(IdleMonitorService.class);
        controller.transformService = transformService;

        Response response = controller.create(transformRequest);
        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());
        Assert.assertEquals(transformResponse, response.getEntity());
    }