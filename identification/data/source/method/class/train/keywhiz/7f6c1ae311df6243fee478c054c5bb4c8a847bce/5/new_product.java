@Timed @ExceptionMetered
  @POST
  @Path("{name}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public ClientDetailResponseV2 modifyClient(@Auth AutomationClient automationClient,
      @PathParam("name") String currentName, @Valid ModifyClientRequestV2 request) {
    Client client = clientDAOReadWrite.getClient(currentName)
        .orElseThrow(NotFoundException::new);
    String newName = request.name();

    // TODO: implement change client (name, updatedAt, updatedBy)
    throw new NotImplementedException(format(
        "Need to implement mutation methods in DAO to rename %s to %s", client.getName(), newName));
  }