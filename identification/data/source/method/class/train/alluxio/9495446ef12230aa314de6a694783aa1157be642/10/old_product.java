@GET
  @Path(GET_VERSION)
  @ReturnType("java.lang.String")
  public Response getVersion() {
    return Response.ok(Version.VERSION).build();
  }