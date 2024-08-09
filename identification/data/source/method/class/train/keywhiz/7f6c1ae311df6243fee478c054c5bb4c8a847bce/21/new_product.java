@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public GroupDetailResponseV2 groupInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAO.getGroup(name)
        .orElseThrow(NotFoundException::new);

    Set<String> secrets = aclDAO.getSanitizedSecretsFor(group).stream()
        .map(SanitizedSecret::name)
        .collect(toSet());

    Set<String> clients = aclDAO.getClientsFor(group).stream()
        .map(Client::getName)
        .collect(toSet());

    return GroupDetailResponseV2.builder()
        .group(group)
        .secrets(secrets)
        .clients(clients)
        .build();
  }