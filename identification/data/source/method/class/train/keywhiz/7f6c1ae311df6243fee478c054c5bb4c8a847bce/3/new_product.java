@Timed @ExceptionMetered
  @PUT
  @Path("{name}/groups")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Iterable<String> modifySecretGroups(@Auth AutomationClient automationClient,
      @PathParam("name") String name, @Valid ModifyGroupsRequestV2 request) {
    // TODO: Use latest version instead of non-versioned
    Secret secret = secretController.getSecretByName(name)
        .orElseThrow(NotFoundException::new);
    String user = automationClient.getName();

    long secretId = secret.getId();
    Set<String> oldGroups = aclDAO.getGroupsFor(secret).stream()
        .map(Group::getName)
        .collect(toSet());

    Set<String> groupsToAdd = Sets.difference(request.addGroups(), oldGroups);
    Set<String> groupsToRemove = Sets.intersection(request.removeGroups(), oldGroups);

    // TODO: should optimize AclDAO to use names and return only name column

    groupsToGroupIds(groupsToAdd)
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAO.findAndAllowAccess(secretId, groupId, auditLog, user, new HashMap<>())));

    groupsToGroupIds(groupsToRemove)
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAO.findAndRevokeAccess(secretId, groupId, auditLog, user, new HashMap<>())));

    return aclDAO.getGroupsFor(secret).stream()
        .map(Group::getName)
        .collect(toSet());
  }