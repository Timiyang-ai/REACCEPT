@Test
  public void testListDir() throws Exception {
    FileOperationService.MkdirRequest request = new FileOperationService.MkdirRequest();
    request.path = "/tmp1";
    fileBrowserService.fileOps().mkdir(request);
    Response response = fileBrowserService.fileOps().listdir("/");
    JSONArray statuses = (JSONArray) response.getEntity();
    System.out.println(response.getEntity());
    Assert.assertEquals(200, response.getStatus());
    Assert.assertTrue(statuses.size() > 0);
    System.out.println(statuses);
  }