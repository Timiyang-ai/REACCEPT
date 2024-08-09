@Timed @ExceptionMetered
  @GET
  @Produces(APPLICATION_JSON)
  public Iterable<String> groupListing(@Auth AutomationClient automationClient) {
    return groupDAOReadOnly.getGroups().stream()
        .map(Group::getName)
        .collect(toSet());
  }