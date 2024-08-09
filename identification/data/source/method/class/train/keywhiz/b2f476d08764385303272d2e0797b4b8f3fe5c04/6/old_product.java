@Timed @ExceptionMetered
  @POST
  @Consumes(APPLICATION_JSON)
  public Response createSecret(@Auth AutomationClient automationClient,
      @Valid CreateSecretRequestV2 request) {
    // allows new version, return version in resulting path
    String name = request.name();

    SecretBuilder builder = secretController
        .builder(name, request.content(), automationClient.getName(), request.expiry())
        .withDescription(request.description())
        .withMetadata(request.metadata())
        .withType(request.type());

    Secret secret;
    try {
      secret = builder.create();
    } catch (DataAccessException e) {
      logger.warn(format("Cannot create secret %s", name), e);
      throw new ConflictException(format("Cannot create secret %s.", name));
    }

    long secretId = secret.getId();
    groupsToGroupIds(request.groups())
        .forEach((maybeGroupId) -> maybeGroupId.ifPresent(
            (groupId) -> aclDAO.findAndAllowAccess(secretId, groupId)));

    UriBuilder uriBuilder = UriBuilder.fromResource(SecretResource.class).path(name);

    return Response.created(uriBuilder.build()).build();
  }