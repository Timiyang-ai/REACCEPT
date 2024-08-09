@GET @Timed @Metered(name="QPS") @ExceptionMetered(name="ExceptionQPS")
  @Path("{name}")
  @Produces(APPLICATION_JSON)
  public ClientDetailResponseV2 clientInfo(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    Client client = clientDAO.getClient(name)
        .orElseThrow(NotFoundException::new);

    return ClientDetailResponseV2.fromClient(client);
  }