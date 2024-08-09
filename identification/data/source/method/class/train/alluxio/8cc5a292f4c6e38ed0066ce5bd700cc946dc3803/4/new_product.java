@POST
  @Path(PATH_PARAM + GET_STATUS)
  @ApiOperation(value = "Get the file status of the path",
      response = alluxio.client.file.URIStatus.class)
  public Response getStatus(@PathParam("path") final String path, final GetStatusOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<URIStatus>() {
      @Override
      public URIStatus call() throws Exception {
        if (options == null) {
          return mFileSystem.getStatus(new AlluxioURI(path), GetStatusOptions.defaults());
        } else {
          return mFileSystem.getStatus(new AlluxioURI(path), options);
        }
      }
    });
  }