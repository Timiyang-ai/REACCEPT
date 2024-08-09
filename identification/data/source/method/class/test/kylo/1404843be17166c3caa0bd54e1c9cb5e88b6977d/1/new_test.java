    @Test
    public void getTable() throws Exception {
        // Mock transform objects
        TransformJob pendingJob = Mockito.mock(TransformJob.class);
        Mockito.when(pendingJob.getGroupId()).thenReturn("PendingJob");
        Mockito.when(pendingJob.progress()).thenReturn(0.5);

        TransformJob successJob = Mockito.mock(TransformJob.class);
        TransformResponse successResponse = new TransformResponse();
        Mockito.when(successJob.get()).thenReturn(successResponse);
        Mockito.when(successJob.isDone()).thenReturn(true);

        TransformService transformService = Mockito.mock(TransformService.class);
        Mockito.when(transformService.getTransformJob("PendingJob")).thenReturn(pendingJob);
        Mockito.when(transformService.getTransformJob("SuccessJob")).thenReturn(successJob);

        // Test with pending job
        AbstractTransformController controller = new AbstractTransformController() {
        };
        controller.transformService = transformService;

        Response response = controller.getTable("PendingJob");
        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());

        TransformResponse transformResponse = (TransformResponse) response.getEntity();
        Assert.assertEquals(0.5, transformResponse.getProgress(), 0.001);
        Assert.assertEquals(TransformResponse.Status.PENDING, transformResponse.getStatus());
        Assert.assertEquals("PendingJob", transformResponse.getTable());

        // Test with success job
        response = controller.getTable("SuccessJob");
        Assert.assertEquals(successResponse, response.getEntity());
        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());
    }