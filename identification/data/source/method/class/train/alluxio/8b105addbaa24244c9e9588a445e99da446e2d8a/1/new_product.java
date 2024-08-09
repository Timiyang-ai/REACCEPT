@GET
  @Path(GET_CONFIGURATION)
  @ReturnType("java.util.SortedMap<java.lang.String, java.lang.String>")
  public Response getConfiguration() {
    return RestUtils.call(new RestUtils.RestCallable<Map<String, String>>() {
      @Override
      public Map<String, String> call() throws Exception {
        Set<Map.Entry<String, String>> properties = Configuration.toMap().entrySet();
        SortedMap<String, String> configuration = new TreeMap<>();
        for (Map.Entry<String, String> entry : properties) {
          String key = entry.getKey();
          if (key.startsWith(ALLUXIO_CONF_PREFIX)) {
            configuration.put(key, entry.getValue());
          }
        }
        return configuration;
      }
    });
  }