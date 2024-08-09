@Timed @ExceptionMetered
  @DELETE
  @Path("{name}")
  public Response deleteSecretSeries(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    secretDAO.getSecretByNameOne(name)
        .orElseThrow(NotFoundException::new);
    secretDAO.deleteSecretsByName(name);
    return Response.noContent().build();
  }