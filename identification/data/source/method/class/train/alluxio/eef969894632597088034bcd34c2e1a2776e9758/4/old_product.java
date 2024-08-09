@POST
  @Path(PATH_PARAM + DELETE)
  @ReturnType("java.lang.Void")
  public Response delete(@PathParam("path") final String path, final DeletePOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Void>() {
      @Override
      public Void call() throws Exception {
        if (options == null) {
          mFileSystem.delete(new AlluxioURI(path), DeletePOptions.getDefaultInstance());
        } else {
          mFileSystem.delete(new AlluxioURI(path), options);
        }
        return null;
      }
    }, ServerConfiguration.global());
  }