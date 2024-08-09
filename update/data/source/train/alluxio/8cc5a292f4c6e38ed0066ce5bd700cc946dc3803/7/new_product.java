@POST
  @Path(PATH_PARAM + OPEN_FILE)
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Opens the given path for reading, use the id with the stream api",
      response = java.lang.Integer.class)
  public Response openFile(@PathParam("path") final String path, final OpenFileOptions options) {
    return RestUtils.call(new RestUtils.RestCallable<Integer>() {
      @Override
      public Integer call() throws Exception {
        FileInStream is;
        if (options == null) {
          is = mFileSystem.openFile(new AlluxioURI(path), OpenFileOptions.defaults());
        } else {
          is = mFileSystem.openFile(new AlluxioURI(path), options);
        }
        return mStreamCache.put(is);
      }
    });
  }