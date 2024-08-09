@GET
  @Path(GET_VERSION)
  @ReturnType("java.lang.String")
  @Deprecated
  public Response getVersion() {
    return RestUtils.call(() -> RuntimeConstants.VERSION, ServerConfiguration.global());
  }