@GET
  @Path(GET_UFS_FREE_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsFreeBytes() {
    try {
      return Response.ok(mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_FREE)).build();
    } catch (IOException e) {
      LOG.warn(e.getMessage());
      return Response.serverError().entity(e.getMessage()).build();
    }
  }