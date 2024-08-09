@Timed @ExceptionMetered
  @DELETE
  @Path("{name}")
  public Response deleteSecretSeries(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Secret secret = secretController.getSecretByName(name).orElseThrow(() -> new NotFoundException("Secret series not found."));

    // Get the groups for this secret so they can be restored manually if necessary
    Set<String> groups = aclDAO.getGroupsFor(secret).stream().map(Group::getName).collect(toSet());

    secretDAO.deleteSecretsByName(name);

    // Record the deletion in the audit log
    Map<String, String> extraInfo = new HashMap<>();
    extraInfo.put("groups", groups.toString());
    extraInfo.put("current version", secret.getVersion().toString());
    auditLog.recordEvent(new Event(Instant.now(), EventTag.SECRET_DELETE, automationClient.getName(), name, extraInfo));
    return Response.noContent().build();
  }