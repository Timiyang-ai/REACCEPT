@GET
  @Path(GET_UFS_USED_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsUsedBytes() {
    try {
      return RestUtils
          .createResponse(mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_USED));
    } catch (IOException e) {
      LOG.warn(e.getMessage());
      return RestUtils.createErrorResponse(e.getMessage());
    }
  }