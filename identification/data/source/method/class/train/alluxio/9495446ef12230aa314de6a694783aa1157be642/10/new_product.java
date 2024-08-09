@GET
  @Path(GET_VERSION)
  @ReturnType("java.lang.String")
  public Response getVersion() {
    // Need to encode the string as JSON because Jackson will not do it automatically.
    return Response.ok(FormatUtils.encodeJson(Version.VERSION)).build();
  }