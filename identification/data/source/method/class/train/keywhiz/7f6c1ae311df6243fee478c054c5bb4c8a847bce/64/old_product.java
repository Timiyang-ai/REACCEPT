@Timed
  @POST
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public SecretDetailResponseV2 modifySecretSeries(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    SecretSeries secret = secretSeriesDAO.getSecretSeriesByName(name)
        .orElseThrow(NotFoundException::new);
    // TODO: DAO to mutate metadata, name
    throw new NotImplementedException("Need to implement mutation methods in DAO for secret " +
        secret.name());
  }