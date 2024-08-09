@Timed @ExceptionMetered
  @DELETE
  @Path("{name}")
  public Response deleteGroup(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAOReadWrite.getGroup(name)
        .orElseThrow(NotFoundException::new);

    // Group memberships are deleted automatically by DB cascading.
    groupDAOReadWrite.deleteGroup(group);
    auditLog.recordEvent(new Event(Instant.now(), EventTag.GROUP_DELETE, automationClient.getName(), group.getName()));
    return Response.noContent().build();
  }