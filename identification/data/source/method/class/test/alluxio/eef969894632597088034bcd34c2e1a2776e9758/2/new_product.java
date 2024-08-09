@POST
  @Path(PATH_PARAM + LIST_STATUS)
  @ReturnType("java.util.List<alluxio.client.file.URIStatus>")
  public Response listStatus(@PathParam("path") final String path,
      final ListStatusPOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<List<URIStatus>>() {
      @Override
      public List<URIStatus> call() throws Exception {
        if (options == null) {
          return mFileSystem
              .listStatus(new AlluxioURI(path));
        } else {
          return mFileSystem.listStatus(new AlluxioURI(path), options);
        }
      }
    }, ServerConfiguration.global());
  }