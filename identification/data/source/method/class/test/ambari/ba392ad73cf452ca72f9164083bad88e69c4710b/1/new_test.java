@Test
  public void testUploadFile() throws Exception {
    Response response = uploadFile("/tmp/", "testUpload", ".tmp", "Hello world");
    Assert.assertEquals(200, response.getStatus());
    Response listdir = fileBrowserService.fileOps().listdir("/tmp");
    JSONArray statuses = (JSONArray) listdir.getEntity();
    System.out.println(statuses.size());
    Response response2 = fileBrowserService.download().browse("/tmp/testUpload.tmp", false, httpHeaders, uriInfo);
    Assert.assertEquals(200, response2.getStatus());
  }