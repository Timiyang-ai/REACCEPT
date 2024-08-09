@GET
  @Path(GET_CONFIGURATION)
  @Deprecated
  public Response getConfiguration() {
    return RestUtils.call(new RestUtils.RestCallable<Map<String, String>>() {
      @Override
      public Map<String, String> call() throws Exception {
        return getConfigurationInternal(true);
      }
    });
  }