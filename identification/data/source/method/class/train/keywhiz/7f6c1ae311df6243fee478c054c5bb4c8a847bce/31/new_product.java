@Timed @ExceptionMetered
  @PUT
  @Path("{name}/groups")
  @Produces(APPLICATION_JSON)
  public Iterable<String> modifyClientGroups(@Auth AutomationClient automationClient,
      @PathParam("name") String name, @Valid ModifyGroupsRequestV2 request) {
    Client client = clientDAOReadWrite.getClient(name)
        .orElseThrow(NotFoundException::new);
    String user = automationClient.getName();

    long clientId = client.getId();
    Set<String> oldGroups = aclDAOReadWrite.getGroupsFor(client).stream()
        .map(Group::getName)
        .collect(toSet());

    Set<String> groupsToAdd = Sets.difference(request.addGroups(), oldGroups);
    Set<String> groupsToRemove = Sets.intersection(request.removeGroups(), oldGroups);

    // TODO: should optimize AclDAO to use names and return only name column

    groupsToGroupIds(groupsToAdd)
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAOReadWrite.findAndEnrollClient(clientId, groupId, auditLog, user, new HashMap<>())));

    groupsToGroupIds(groupsToRemove)
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAOReadWrite.findAndEvictClient(clientId, groupId, auditLog, user, new HashMap<>())));

    return aclDAOReadWrite.getGroupsFor(client).stream()
        .map(Group::getName)
        .collect(toSet());
  }