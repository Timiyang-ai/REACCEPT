@Ignore
    @Test
    public void testListDir() throws IOException, Exception {
        FileOperationService.MkdirRequest request = new FileOperationService.MkdirRequest();
        request.path = "/tmp1";
        fileBrowserService.fileOps().mkdir(request, httpHeaders, uriInfo);
        Response response = fileBrowserService.fileOps().listdir("/", httpHeaders,
            uriInfo);
        JSONArray statuses = (JSONArray) response.getEntity();
        System.out.println(response.getEntity());
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(statuses.size() > 0);
        System.out.println(statuses);
    }