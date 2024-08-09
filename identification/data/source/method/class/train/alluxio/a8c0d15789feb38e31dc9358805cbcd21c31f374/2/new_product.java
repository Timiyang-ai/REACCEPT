@POST
  @Path(CREATE_FILE)
  @ReturnType("java.lang.Void")
  public Response createFile(@QueryParam("path") String path,
      @QueryParam("persisted") Boolean persisted, @QueryParam("recursive") Boolean recursive,
      @QueryParam("blockSizeBytes") Long blockSizeBytes, @QueryParam("ttl") Long ttl) {
    try {
      Preconditions.checkNotNull(path, "required 'path' parameter is missing");
      CreateFileOptions.Builder options = new CreateFileOptions.Builder(MasterContext.getConf());
      if (persisted != null) {
        options.setPersisted(persisted);
      }
      if (recursive != null) {
        options.setRecursive(recursive);
      }
      if (blockSizeBytes != null) {
        options.setBlockSizeBytes(blockSizeBytes);
      }
      if (ttl != null) {
        options.setTtl(ttl);
      }
      mFileSystemMaster.create(new AlluxioURI(path), options.build());
      return Response.ok().build();
    } catch (AlluxioException | IOException | NullPointerException e) {
      LOG.warn(e.getMessage());
      return Response.serverError().entity(e.getMessage()).build();
    }
  }