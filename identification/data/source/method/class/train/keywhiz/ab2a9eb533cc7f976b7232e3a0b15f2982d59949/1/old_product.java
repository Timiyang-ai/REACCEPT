@Path("{name}")
  @Timed @ExceptionMetered
  @POST
  @Consumes(APPLICATION_JSON)
  public Response createOrUpdateSecret(@Auth User user, @PathParam("name") String secretName, @Valid CreateOrUpdateSecretRequestV2 request) {

    logger.info("User '{}' createOrUpdate secret '{}'.", user, secretName);

    Secret secret = secretController
        .builder(secretName, request.content(), user.getName(), request.expiry())
        .withDescription(request.description())
        .withMetadata(request.metadata())
        .withType(request.type())
        .createOrUpdate();

    URI uri = UriBuilder.fromResource(SecretsResource.class).path(secretName).build();

    return Response.created(uri).entity(secretDetailResponseFromId(secret.getId())).build();
  }