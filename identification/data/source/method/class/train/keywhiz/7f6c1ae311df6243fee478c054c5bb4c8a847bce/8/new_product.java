@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public SecretDetailResponseV2 secretInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    SecretSeriesAndContent secret = secretDAO.getSecretByName(name)
        .orElseThrow(NotFoundException::new);
    return SecretDetailResponseV2.builder()
        .series(secret.series())
        .checksum(secret.content().hmac())
        .expiry(secret.content().expiry())
        .build();
  }