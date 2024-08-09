@Timed @ExceptionMetered
  @Path("{name}/backfill-expiration")
  @POST
  public boolean backfillExpiration(@Auth AutomationClient automationClient, @PathParam("name") String name) {
    Optional<Secret> secretOptional = secretController.getSecretByName(name);
    if (!secretOptional.isPresent()) {
      throw new NotFoundException("No such secret: " + name);
    }

    Secret secret = secretOptional.get();
    byte[] content = Base64.getDecoder().decode(secret.getSecret());

    Instant expiry = null;
    if (secret.getName().endsWith(".crt") || secret.getName().endsWith(".pem")) {
      expiry = ExpirationExtractor.expirationFromEncodedCertificateChain(content);
    } else if (secret.getName().endsWith(".gpg") || secret.getName().endsWith(".pgp")) {
      expiry = ExpirationExtractor.expirationFromOpenPGP(content);
    }

    if (expiry != null) {
      logger.info("Found expiry for secret {}: {}", secret.getName(), expiry.getEpochSecond());
      return secretDAO.setExpiration(name, expiry);
    }

    logger.info("Unable to determine expiry for secret {}", secret.getName());
    return false;
  }