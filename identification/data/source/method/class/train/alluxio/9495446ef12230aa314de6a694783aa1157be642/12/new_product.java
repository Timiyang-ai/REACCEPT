@GET
  @Path(GET_VERSION)
  @ReturnType("java.lang.String")
  public Response getVersion() {
    return RestUtils.createResponse(RuntimeConstants.VERSION);
  }