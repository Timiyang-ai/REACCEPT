@POST @Timed
  @Consumes(APPLICATION_JSON)
  public Response createGroup(@Auth AutomationClient automationClient,
      @Valid CreateGroupRequestV2 request) {
    String creator = automationClient.getName();
    String group = request.name();

    groupDAO.getGroup(group).ifPresent((g) -> {
      logger.info("Automation ({}) - Group {} already exists", creator, group);
      throw new ConflictException(format("Group %s already exists", group));
    });

    groupDAO.createGroup(group, creator, request.description());
    URI uri = UriBuilder.fromResource(GroupResource.class).path(group).build();
    return Response.created(uri).build();
  }