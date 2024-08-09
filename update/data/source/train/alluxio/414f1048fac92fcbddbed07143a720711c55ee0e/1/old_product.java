@POST
  @Path(FREE)
  @ReturnType("java.lang.Void")
  public Response free(@QueryParam("path") final String path,
      @QueryParam("recursive") final boolean recursive) {
    return RestUtils.call(new RestUtils.RestCallable<Void>() {
      @Override
      public Void call() throws Exception {
        Preconditions.checkNotNull(path, "required 'path' parameter is missing");
        mFileSystemMaster.free(new AlluxioURI(path),
            FreeOptions.defaults().setRecursive(recursive).setForced(true));
        return null;
      }
    });
  }