@POST
  @Path(PATH_PARAM + LIST_STATUS)
  @ApiOperation(value = "List the URIStatuses of the path's children",
      response = java.util.List.class)
  public Response listStatus(@PathParam("path") final String path,
      final ListStatusOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<List<URIStatus>>() {
      @Override
      public List<URIStatus> call() throws Exception {
        if (options == null) {
          return mFileSystem.listStatus(new AlluxioURI(path), ListStatusOptions.defaults());
        } else {
          return mFileSystem.listStatus(new AlluxioURI(path), options);
        }
      }
    });
  }