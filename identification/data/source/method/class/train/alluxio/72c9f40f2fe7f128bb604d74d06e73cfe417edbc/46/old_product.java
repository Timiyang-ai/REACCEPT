@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsCapacityBytes() {
    try {
      return Response.ok(mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_TOTAL)).build();
    } catch (IOException e) {
      LOG.warn(e.getMessage());
      return Response.serverError().entity(e.getMessage()).build();
    }
  }