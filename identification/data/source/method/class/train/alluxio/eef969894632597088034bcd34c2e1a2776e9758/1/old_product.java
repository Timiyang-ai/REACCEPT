@POST
  @Path(PATH_PARAM + RENAME)
  @ReturnType("java.lang.Void")
  public Response rename(@PathParam("path") final String path, @QueryParam("dst") final String dst,
      final RenamePOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Void>() {
      @Override
      public Void call() throws Exception {
        Preconditions.checkNotNull(dst, "required 'dst' parameter is missing");
        if (options == null) {
          mFileSystem.rename(new AlluxioURI(path), new AlluxioURI(dst),
              RenamePOptions.getDefaultInstance());
        } else {
          mFileSystem.rename(new AlluxioURI(path), new AlluxioURI(dst), options);
        }
        return null;
      }
    }, ServerConfiguration.global());
  }