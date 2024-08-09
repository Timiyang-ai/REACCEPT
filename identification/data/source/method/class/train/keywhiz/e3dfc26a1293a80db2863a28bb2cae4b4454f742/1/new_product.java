@Timed @ExceptionMetered
  @GET
  @Path("{name}/clients")
  @Produces(APPLICATION_JSON)
  public Set<Client> clientDetailForGroup(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAOReadOnly.getGroup(name)
        .orElseThrow(NotFoundException::new);

    return aclDAOReadOnly.getClientsFor(group);
  }