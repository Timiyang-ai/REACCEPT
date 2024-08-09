@Timed @ExceptionMetered
  @GET
  @Produces(APPLICATION_JSON)
  public Iterable<String> clientListing(@Auth AutomationClient automationClient) {
    return clientDAOReadOnly.getClients().stream()
        .map(Client::getName)
        .collect(toSet());
  }