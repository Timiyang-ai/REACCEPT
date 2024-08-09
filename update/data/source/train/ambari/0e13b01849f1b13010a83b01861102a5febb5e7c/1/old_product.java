@GET
  @Produces("text/plain")
  public Response getHosts(String body, @Context HttpHeaders headers, @Context UriInfo ui) {
    return handleRequest(headers, body, ui, Request.Type.GET,
        createHostResource(m_clusterName, null, ui));
  }