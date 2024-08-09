@Timed @ExceptionMetered
  @Path("{name}/backfill-expiration")
  @POST
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public boolean backfillExpiration(@Auth AutomationClient automationClient, @PathParam("name") String name, List<String> passwords) {
    Optional<Secret> secretOptional = secretController.getSecretByName(name);
    if (!secretOptional.isPresent()) {
      throw new NotFoundException("No such secret: " + name);
    }

    Secret secret = secretOptional.get();
    String secretName = secret.getName();
    byte[] secretContent = Base64.getDecoder().decode(secret.getSecret());

    // Always try empty password
    passwords.add("");

    Instant expiry = null;
    if (secretName.endsWith(".crt") || secretName.endsWith(".pem") || secretName.endsWith(".key")) {
      expiry = ExpirationExtractor.expirationFromEncodedCertificateChain(secretContent);
    } else if (secretName.endsWith(".gpg") || secretName.endsWith(".pgp")) {
      expiry = ExpirationExtractor.expirationFromOpenPGP(secretContent);
    } else if (secretName.endsWith(".p12") || secretName.endsWith(".pfx")) {
      while (expiry == null && !passwords.isEmpty()) {
        String password = passwords.remove(0);
        expiry = ExpirationExtractor.expirationFromKeystore("PKCS12", password, secretContent);
      }
    } else if (secretName.endsWith(".jceks")) {
      while (expiry == null && !passwords.isEmpty()) {
        String password = passwords.remove(0);
        expiry = ExpirationExtractor.expirationFromKeystore("JCEKS", password, secretContent);
      }
    } else if (secretName.endsWith(".jks")) {
      while (expiry == null && !passwords.isEmpty()) {
        String password = passwords.remove(0);
        expiry = ExpirationExtractor.expirationFromKeystore("JKS", password, secretContent);
      }
    }

    if (expiry != null) {
      logger.info("Found expiry for secret {}: {}", secretName, expiry.getEpochSecond());
      return secretDAO.setExpiration(name, expiry);
    }

    logger.info("Unable to determine expiry for secret {}", secretName);
    return false;
  }