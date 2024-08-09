@POST
  @Path(PATH_PARAM + CREATE_DIRECTORY)
  @ReturnType("java.lang.Void")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createDirectory(@PathParam("path") final String path,
      final CreateDirectoryPOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Void>() {
      @Override
      public Void call() throws Exception {
        if (options == null) {
          mFileSystem
              .createDirectory(new AlluxioURI(path), CreateDirectoryPOptions.getDefaultInstance());
        } else {
          mFileSystem.createDirectory(new AlluxioURI(path), options);
        }
        return null;
      }
    }, ServerConfiguration.global());
  }