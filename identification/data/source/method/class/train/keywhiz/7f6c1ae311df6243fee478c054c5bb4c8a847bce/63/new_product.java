@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public SecretDetailResponseV2 secretInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    SecretSeries secret = secretSeriesDAO.getSecretSeriesByName(name)
        .orElseThrow(NotFoundException::new);
    List<String> versions = secretController.getVersionsForName(name);
    return SecretDetailResponseV2.builder()
        .series(secret)
        .versions(versions)
        .build();
  }