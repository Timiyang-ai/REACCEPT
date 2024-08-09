@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public SecretDetailResponseV2 secretInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    SecretSeriesAndContent secret = secretDAO.getSecretByNameOne(name)
        .orElseThrow(NotFoundException::new);
    return SecretDetailResponseV2.builder()
        .series(secret.series())
        .build();
  }