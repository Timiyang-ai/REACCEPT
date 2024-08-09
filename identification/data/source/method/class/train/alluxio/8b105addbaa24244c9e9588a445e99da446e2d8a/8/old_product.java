@GET
  @Path(GET_CONFIGURATION)
  @ReturnType("java.util.SortedMap<java.lang.String, java.lang.String>")
  @Deprecated
  public Response getConfiguration() {
    return RestUtils.call(new RestUtils.RestCallable<Map<String, String>>() {
      @Override
      public Map<String, String> call() throws Exception {
        return getConfigurationInternal();
      }
    });
  }