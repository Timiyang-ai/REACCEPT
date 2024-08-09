@GET @Timed @Metered(name="QPS") @ExceptionMetered(name="ExceptionQPS")
  @Produces(APPLICATION_JSON)
  public Iterable<String> secretListing(@Auth AutomationClient automationClient) {
    return secretController.getSanitizedSecrets().stream()
        .map(SanitizedSecret::name)
        .collect(toSet());
  }