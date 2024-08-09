@Timed @ExceptionMetered
  @GET
  @Path("{name}/secrets")
  @Produces(APPLICATION_JSON)
  public Set<SanitizedSecret> secretDetailForGroup(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAOReadOnly.getGroup(name)
        .orElseThrow(NotFoundException::new);

    return aclDAOReadOnly.getSanitizedSecretsFor(group);
  }