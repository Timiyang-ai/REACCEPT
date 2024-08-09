@GET
  @Path(GET_CONFIGURATION)
  @Deprecated
  public Response getConfiguration() {
    return RestUtils.call(() -> getConfigurationInternal(true), ServerConfiguration.global());
  }