@GET
  @Path("{name}/groups")
  @Produces(APPLICATION_JSON)
  public Iterable<String> clientGroupsListing(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Client client = clientDAO.getClient(name)
        .orElseThrow(NotFoundException::new);
    return aclDAO.getGroupsFor(client).stream()
        .map(Group::getName)
        .collect(toSet());
  }