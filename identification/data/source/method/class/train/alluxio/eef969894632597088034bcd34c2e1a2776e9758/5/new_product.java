@POST
  @Path(PATH_PARAM + FREE)
  @ReturnType("java.lang.Void")
  public Response free(@PathParam("path") final String path, final FreePOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Void>() {
      @Override
      public Void call() throws Exception {
        if (options == null) {
          mFileSystem.free(new AlluxioURI(path));
        } else {
          mFileSystem.free(new AlluxioURI(path), options);
        }
        return null;
      }
    }, ServerConfiguration.global());
  }