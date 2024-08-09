@Timed @ExceptionMetered
  @PUT
  @Path("{name}/groups")
  @Produces(APPLICATION_JSON)
  public Iterable<String> modifyClientGroups(@Auth AutomationClient automationClient,
      @PathParam("name") String name, @Valid ModifyGroupsRequestV2 request) {
    Client client = clientDAO.getClient(name)
        .orElseThrow(NotFoundException::new);

    long clientId = client.getId();
    Set<String> oldGroups = aclDAO.getGroupsFor(client).stream()
        .map(Group::getName)
        .collect(toSet());

    Set<String> groupsToAdd = Sets.difference(request.addGroups(), oldGroups);
    Set<String> groupsToRemove = Sets.intersection(request.removeGroups(), oldGroups);

    // TODO: should optimize AclDAO to use names and return only name column

    groupsToGroupIds(groupsToAdd)
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAO.findAndEnrollClient(clientId, groupId)));

    groupsToGroupIds(groupsToRemove)
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAO.findAndEvictClient(clientId, groupId)));

    return aclDAO.getGroupsFor(client).stream()
        .map(Group::getName)
        .collect(toSet());
  }