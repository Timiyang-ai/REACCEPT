@Timed @ExceptionMetered
  @GET
  @Path("{name}/secrets")
  @Produces(APPLICATION_JSON)
  public Set<SanitizedSecret> secretDetailForGroup(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAO.getGroup(name)
        .orElseThrow(NotFoundException::new);

    return aclDAO.getSanitizedSecretsFor(group);
  }