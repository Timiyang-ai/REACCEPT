@POST
  @Path(PATH_PARAM + MOUNT)
  @ReturnType("java.lang.Void")
  public Response mount(@PathParam("path") final String path, @QueryParam("src") final String src,
      final MountPOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Void>() {
      @Override
      public Void call() throws Exception {
        Preconditions.checkNotNull(src, "required 'src' parameter is missing");
        if (options == null) {
          mFileSystem
              .mount(new AlluxioURI(path), new AlluxioURI(src), MountPOptions.getDefaultInstance());
        } else {
          mFileSystem.mount(new AlluxioURI(path), new AlluxioURI(src), options);
        }
        return null;
      }
    }, ServerConfiguration.global());
  }