@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public GroupDetailResponseV2 groupInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAOReadOnly.getGroup(name)
        .orElseThrow(NotFoundException::new);

    Set<String> secrets = aclDAOReadOnly.getSanitizedSecretsFor(group).stream()
        .map(SanitizedSecret::name)
        .collect(toSet());

    Set<String> clients = aclDAOReadOnly.getClientsFor(group).stream()
        .map(Client::getName)
        .collect(toSet());

    return GroupDetailResponseV2.builder()
        .group(group)
        .secrets(secrets)
        .clients(clients)
        .build();
  }