@POST @ApiIgnore // until documented
  @Produces("text/plain")
  public Response createUser(String body, @Context HttpHeaders headers, @Context UriInfo ui) {
    return handleRequest(headers, body, ui, Request.Type.POST, createUserResource(null));
  }