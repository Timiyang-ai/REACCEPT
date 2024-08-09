@POST
  @Path(PATH_PARAM + OPEN_FILE)
  @ReturnType("java.lang.Integer")
  @Produces(MediaType.APPLICATION_JSON)
  public Response openFile(@PathParam("path") final String path, final OpenFilePOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Integer>() {
      @Override
      public Integer call() throws Exception {
        FileInStream is;
        if (options == null) {
          is = mFileSystem.openFile(new AlluxioURI(path), OpenFilePOptions.getDefaultInstance());
        } else {
          is = mFileSystem.openFile(new AlluxioURI(path), options);
        }
        return mStreamCache.put(is);
      }
    }, ServerConfiguration.global());
  }