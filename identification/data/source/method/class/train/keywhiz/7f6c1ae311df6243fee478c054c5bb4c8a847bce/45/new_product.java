@Timed @ExceptionMetered
  @GET
  @Path("{name}/secrets")
  @Produces(APPLICATION_JSON)
  public Iterable<String> clientSecretsListing(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Client client = clientDAOReadOnly.getClient(name)
        .orElseThrow(NotFoundException::new);
    return aclDAOReadOnly.getSanitizedSecretsFor(client).stream()
        .map(SanitizedSecret::name)
        .collect(toSet());
  }