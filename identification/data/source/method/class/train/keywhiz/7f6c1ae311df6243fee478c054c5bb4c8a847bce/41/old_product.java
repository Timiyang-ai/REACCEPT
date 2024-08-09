@DELETE @Timed @Metered(name="QPS") @ExceptionMetered(name="ExceptionQPS")
  @Path("{name}")
  public Response deleteGroup(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Group group = groupDAO.getGroup(name)
        .orElseThrow(NotFoundException::new);

    // Group memberships are deleted automatically by DB cascading.
    groupDAO.deleteGroup(group);
    return Response.noContent().build();
  }