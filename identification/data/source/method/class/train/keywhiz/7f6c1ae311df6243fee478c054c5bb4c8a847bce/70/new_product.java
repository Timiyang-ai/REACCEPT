@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public SecretDetailResponseV2 secretInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    SecretSeries secret = secretSeriesDAO.getSecretSeriesByName(name)
        .orElseThrow(NotFoundException::new);
    return SecretDetailResponseV2.builder()
        .series(secret)
        .build();
  }