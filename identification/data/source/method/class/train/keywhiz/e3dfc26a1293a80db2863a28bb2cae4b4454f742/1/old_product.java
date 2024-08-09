@Timed @ExceptionMetered
  @GET
  @Path("{name}/clients")
  @Produces(APPLICATION_JSON)
  public Set<Client> clientDetailForGroup(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAO.getGroup(name)
        .orElseThrow(NotFoundException::new);

    return aclDAO.getClientsFor(group);
  }