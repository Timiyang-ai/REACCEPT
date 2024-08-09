@GET
  @Path(GET_UFS_FREE_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsFreeBytes() {
    try {
      return RestUtils
          .createResponse(mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_FREE));
    } catch (IOException e) {
      LOG.warn(e.getMessage());
      return RestUtils.createErrorResponse(e.getMessage());
    }
  }