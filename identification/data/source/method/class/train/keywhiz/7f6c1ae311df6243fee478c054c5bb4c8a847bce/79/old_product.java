@DELETE @Timed @Metered(name="QPS") @ExceptionMetered(name="ExceptionQPS")
  @Path("{name}")
  public Response deleteSecretSeries(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    secretSeriesDAO.getSecretSeriesByName(name)
        .orElseThrow(NotFoundException::new);
    secretDAO.deleteSecretsByName(name);
    return Response.noContent().build();
  }