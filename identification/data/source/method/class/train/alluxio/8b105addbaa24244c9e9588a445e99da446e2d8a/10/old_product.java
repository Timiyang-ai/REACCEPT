@GET
  @Path(GET_CONFIGURATION)
  @ReturnType("java.util.Map<String, String>")
  public Response getConfiguration() {
    Set<Map.Entry<Object, Object>> properties = mMasterConf.getInternalProperties().entrySet();
    Map<String, String> configuration = new HashMap<>(properties.size());
    for (Map.Entry<Object, Object> entry : properties) {
      String key = entry.getKey().toString();
      configuration.put(key, mMasterConf.get(key));
    }
    return Response.ok(configuration).build();
  }