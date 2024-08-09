@Timed @ExceptionMetered
  @GET
  @Produces(APPLICATION_JSON)
  public Iterable<String> secretListing(@Auth AutomationClient automationClient) {
    return secretController.getSanitizedSecrets(null, null).stream()
        .map(SanitizedSecret::name)
        .collect(toSet());
  }