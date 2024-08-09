@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsCapacityBytes() {
    try {
      return RestUtils
          .createResponse(mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_TOTAL));
    } catch (IOException e) {
      LOG.warn(e.getMessage());
      return RestUtils.createErrorResponse(e.getMessage());
    }
  }