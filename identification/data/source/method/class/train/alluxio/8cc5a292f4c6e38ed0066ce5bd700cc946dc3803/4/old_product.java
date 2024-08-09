@POST
  @Path(PATH_PARAM + GET_STATUS)
  @ReturnType("alluxio.client.file.URIStatus")
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