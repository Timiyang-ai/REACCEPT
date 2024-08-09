@GET
  @Path(GET_VERSION)
  @Deprecated
  public Response getVersion() {
    return RestUtils.call(() -> RuntimeConstants.VERSION, ServerConfiguration.global());
  }