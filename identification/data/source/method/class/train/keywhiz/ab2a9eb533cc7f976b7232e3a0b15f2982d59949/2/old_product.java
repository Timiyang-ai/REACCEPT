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

    Response response = Response.created(uri).entity(secretDetailResponseFromId(secret.getId())).build();

    if (response.getStatus() == HttpStatus.SC_CREATED) {
      Map<String, String> extraInfo = new HashMap<>();
      if (request.description() != null) {
        extraInfo.put("description", request.description());
      }
      if (request.metadata() != null) {
        extraInfo.put("metadata", request.metadata().toString());
      }
      extraInfo.put("expiry", Long.toString(request.expiry()));
      auditLog.recordEvent(new Event(Instant.now(), EventTag.SECRET_CREATEORUPDATE, user.getName(), secretName, extraInfo));
    }
    return response;
  }