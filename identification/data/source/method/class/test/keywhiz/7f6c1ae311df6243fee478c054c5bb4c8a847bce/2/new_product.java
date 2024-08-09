@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public SecretDetailResponseV2 secretInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    SecretSeriesAndContent secret = secretDAO.getSecretByName(name)
        .orElseThrow(NotFoundException::new);
    return SecretDetailResponseV2.builder()
        .seriesAndContent(secret)
        .build();
  }