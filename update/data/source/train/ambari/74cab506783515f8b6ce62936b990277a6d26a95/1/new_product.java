@GET
  @Produces("text/plain")
  public Response getClusters(String body, @Context HttpHeaders headers, @Context UriInfo ui) {
    return handleRequest(headers, body, ui, Request.Type.GET, createClusterResource(null));
  }