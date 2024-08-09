@Timed @ExceptionMetered
  @GET
  @Produces(APPLICATION_JSON)
  public Iterable<String> secretListing(@Auth AutomationClient automationClient,
      @QueryParam("idx") Integer idx, @QueryParam("num") Integer num,
      @DefaultValue("true") @QueryParam("newestFirst") boolean newestFirst) {
    if (idx != null && num != null) {
      if (idx < 0 || num < 0) {
        throw new BadRequestException(
            "Index and num must both be positive when retrieving batched secrets!");
      }
      return secretController.getSecretsBatched(idx, num, newestFirst).stream()
          .map(SanitizedSecret::name)
          .collect(toList());
    }
    return secretController.getSanitizedSecrets(null, null).stream()
        .map(SanitizedSecret::name)
        .collect(toSet());
  }