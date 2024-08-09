@GET
  @Produces("text/plain")
  public Response getClusters(String body, @Context HttpHeaders headers, @Context UriInfo ui) {

    hasPermission(Request.Type.GET, null);
    return handleRequest(headers, body, ui, Request.Type.GET, createClusterResource(null));
  }