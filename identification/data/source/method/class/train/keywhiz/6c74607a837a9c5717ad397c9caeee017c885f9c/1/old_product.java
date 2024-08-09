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

    UriBuilder uriBuilder = UriBuilder.fromResource(SecretResource.class).path(name);

    return Response.created(uriBuilder.build()).build();
  }