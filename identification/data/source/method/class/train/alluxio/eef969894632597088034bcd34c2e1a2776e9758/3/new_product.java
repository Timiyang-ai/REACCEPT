@POST
  @Path(PATH_PARAM + CREATE_DIRECTORY)
  @ReturnType("java.lang.Void")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createDirectory(@PathParam("path") final String path,
      final CreateDirectoryPOptions options) {
    return RestUtils.call((RestUtils.RestCallable<Void>) () -> {
      if (options == null) {
        mFileSystem.createDirectory(new AlluxioURI(path));
      } else {
        mFileSystem.createDirectory(new AlluxioURI(path), options);
      }
      return null;
    }, ServerConfiguration.global());
  }