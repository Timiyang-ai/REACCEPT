@Timed @ExceptionMetered
  @POST
  @Consumes(APPLICATION_JSON)
  public Response createClient(@Auth AutomationClient automationClient,
      @Valid CreateClientRequestV2 request) {
    String creator = automationClient.getName();
    String client = request.name();

    clientDAOReadWrite.getClient(client).ifPresent((c) -> {
      logger.info("Automation ({}) - Client {} already exists", creator, client);
      throw new ConflictException("Client name already exists.");
    });

    // Creates new client record
    long clientId = clientDAOReadWrite.createClient(client, creator, request.description());
    auditLog.recordEvent(new Event(Instant.now(), EventTag.CLIENT_CREATE, creator, client));

    // Enrolls client in any requested groups
    groupsToGroupIds(request.groups())
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAOReadWrite.findAndEnrollClient(clientId, groupId, auditLog, creator, new HashMap<>())));

    URI uri = UriBuilder.fromResource(ClientResource.class).path(client).build();
    return Response.created(uri).build();
  }