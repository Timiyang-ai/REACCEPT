@Timed @ExceptionMetered
  @DELETE
  @Path("{name}")
  public Response deleteSecretSeries(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    secretDAO.getSecretByName(name)
        .orElseThrow(NotFoundException::new);
    secretDAO.deleteSecretsByName(name);
    auditLog.recordEvent(new Event(Instant.now(), EventTag.SECRET_DELETE, automationClient.getName(), name));
    return Response.noContent().build();
  }