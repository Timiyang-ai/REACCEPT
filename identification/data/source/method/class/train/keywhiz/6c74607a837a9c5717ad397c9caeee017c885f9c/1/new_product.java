@Timed @ExceptionMetered
  @Path("{name}")
  @POST
  @Consumes(APPLICATION_JSON)
  public Response createOrUpdateSecret(@Auth AutomationClient automationClient,
                                       @PathParam("name") String name,
                                       @Valid CreateOrUpdateSecretRequestV2 request) {
    SecretBuilder builder = secretController
        .builder(name, request.content(), automationClient.getName(), request.expiry())
        .withDescription(request.description())
        .withMetadata(request.metadata())
        .withType(request.type());

    builder.createOrUpdate();

    Map<String, String> extraInfo = new HashMap<>();
    if (request.description() != null) {
      extraInfo.put("description", request.description());
    }
    if (request.metadata() != null) {
      extraInfo.put("metadata", request.metadata().toString());
    }
    extraInfo.put("expiry", Long.toString(request.expiry()));
    auditLog.recordEvent(new Event(Instant.now(), EventTag.SECRET_CREATEORUPDATE, automationClient.getName(), name, extraInfo));

    UriBuilder uriBuilder = UriBuilder.fromResource(SecretResource.class).path(name);

    return Response.created(uriBuilder.build()).build();
  }