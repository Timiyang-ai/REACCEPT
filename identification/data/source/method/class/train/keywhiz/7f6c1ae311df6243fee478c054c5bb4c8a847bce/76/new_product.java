@Timed @ExceptionMetered
  @GET
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public ClientDetailResponseV2 clientInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Client client = clientDAOReadOnly.getClient(name)
        .orElseThrow(NotFoundException::new);

    return ClientDetailResponseV2.fromClient(client);
  }