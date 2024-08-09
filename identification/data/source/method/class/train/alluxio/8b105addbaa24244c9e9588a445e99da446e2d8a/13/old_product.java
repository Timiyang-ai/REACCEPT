@GET
  @Path(GET_CONFIGURATION)
  @ReturnType("java.util.SortedMap<String, String>")
  public Response getConfiguration() {
    Set<Map.Entry<Object, Object>> properties = mMasterConf.toMap().entrySet();
    SortedMap<String, String> configuration = new TreeMap<>();
    for (Map.Entry<Object, Object> entry : properties) {
      String key = entry.getKey().toString();
      if (key.startsWith(ALLUXIO_CONF_PREFIX)) {
        configuration.put(key, (String) entry.getValue());
      }
    }
    return RestUtils.createResponse(configuration);
  }