@GET
  @Path(GET_UFS_USED_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsUsedBytes() {
    try {
      return Response.ok(mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_USED)).build();
    } catch (IOException e) {
      LOG.warn(e.getMessage());
      return Response.serverError().entity(e.getMessage()).build();
    }
  }