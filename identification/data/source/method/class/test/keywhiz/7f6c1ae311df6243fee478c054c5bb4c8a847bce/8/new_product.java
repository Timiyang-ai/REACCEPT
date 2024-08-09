@GET @Timed
  @Path("{name}/secrets")
  @Produces(APPLICATION_JSON)
  public Iterable<String> clientSecretsListing(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Client client = clientDAO.getClient(name)
        .orElseThrow(NotFoundException::new);
    return aclDAO.getSanitizedSecretsFor(client).stream()
        .map(SanitizedSecret::name)
        .collect(toSet());
  }