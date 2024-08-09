@POST
  @Path(PATH_PARAM + SET_ATTRIBUTE)
  @ReturnType("java.lang.Void")
  public Response setAttribute(@PathParam("path") final String path,
      final SetAttributePOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Void>() {
      @Override
      public Void call() throws Exception {
        if (options == null) {
          mFileSystem.setAttribute(new AlluxioURI(path));
        } else {
          mFileSystem.setAttribute(new AlluxioURI(path), options);
        }
        return null;
      }
    }, ServerConfiguration.global());
  }