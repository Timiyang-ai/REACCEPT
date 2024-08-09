@GET @Timed @Metered(name="QPS") @ExceptionMetered(name="ExceptionQPS")
  @Produces(APPLICATION_JSON)
  public Iterable<String> clientListing(@Auth AutomationClient automationClient) {
    return clientDAO.getClients().stream()
        .map(Client::getName)
        .collect(toSet());
  }