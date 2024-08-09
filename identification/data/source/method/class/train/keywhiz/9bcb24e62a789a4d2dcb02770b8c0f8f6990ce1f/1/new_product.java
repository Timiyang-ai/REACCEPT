@Path("{name}/partialupdate")
  @Timed @ExceptionMetered
  @POST
  @Consumes(APPLICATION_JSON)
  public Response partialUpdateSecret(@Auth User user, @PathParam("name") String secretName, @Valid
      PartialUpdateSecretRequestV2 request) {

    logger.info("User '{}' partialUpdate secret '{}'.", user, secretName);

    long id = secretDAOReadWrite.partialUpdateSecret(secretName, user.getName(), request);

    URI uri = UriBuilder.fromResource(SecretsResource.class)
        .path(secretName)
        .path("partialupdate")
        .build();

    Response response = Response.created(uri).entity(secretDetailResponseFromId(id)).build();

    if (response.getStatus() == HttpStatus.SC_CREATED) {
      Map<String, String> extraInfo = new HashMap<>();
      if (request.descriptionPresent()) {
        extraInfo.put("description", request.description());
      }
      if (request.metadataPresent()) {
        extraInfo.put("metadata", request.metadata().toString());
      }
      if (request.expiryPresent()) {
        extraInfo.put("expiry", Long.toString(request.expiry()));
      }
      auditLog.recordEvent(
          new Event(Instant.now(), EventTag.SECRET_UPDATE, user.getName(), secretName, extraInfo));
    }
    return response;
  }