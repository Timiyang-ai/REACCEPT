@Timed
  @GET
  @Produces(APPLICATION_JSON)
  public Iterable<String> groupListing(@Auth AutomationClient automationClient) {
    return groupDAO.getGroups().stream()
        .map(Group::getName)
        .collect(toSet());
  }