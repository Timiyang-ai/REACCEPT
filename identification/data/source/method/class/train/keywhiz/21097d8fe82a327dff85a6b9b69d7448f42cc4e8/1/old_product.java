@Timed @ExceptionMetered
  @GET @Path("{group}")
  @Produces(APPLICATION_OCTET_STREAM)
  public byte[] backup(
      @Auth AutomationClient automationClient,
      @PathParam("group") String name) {
    Optional<Group> groupOptional = groupDAO.getGroup(name);
    if (!groupOptional.isPresent()) {
      throw new NotFoundException("Unknown group: " + name);
    }

    if (config.getBackupExportKey() == null) {
      throw new ServiceUnavailableException("Backups keys are not configured");
    }

    Group group = groupOptional.get();

    // SecretDeliveryResponse is the same data a client receives when requesting a secret,
    // so it should have all the relevant information we need (including content, checksum).
    List<SecretDeliveryResponse> secrets = secretController.getSecretsForGroup(group).stream()
        .map(SecretDeliveryResponse::fromSecret)
        .collect(toList());

    String serialized;
    try {
      serialized = objectMapper.writeValueAsString(secrets);
    } catch (JsonProcessingException e) {
      // This should never happen
      logger.error("Unable to backup secrets", e);
      throw new InternalServerErrorException("Unable to backup secrets, check logs for details");
    }

    // Record all checksums of backed up/exported secrets so we can uniquely identify which
    // particular contents were returned in the response from inspection of the audit log.
    Map<String, String> auditInfo = secrets.stream()
        .collect(toMap(SecretDeliveryResponse::getName, SecretDeliveryResponse::getChecksum));

    // Record audit event
    auditLog.recordEvent(new Event(
        now(),
        GROUP_BACKUP,
        automationClient.getName(),
        group.getName(),
        auditInfo));

    // Perform encryption & return encrypted data
    try {
      Key exportKey = new Key(config.getBackupExportKey());

      Encryptor encryptor = new Encryptor(exportKey);
      encryptor.setEncryptionAlgorithm(AES256);
      encryptor.setSigningAlgorithm(Unsigned);
      encryptor.setCompressionAlgorithm(ZIP);

      ByteArrayInputStream plaintext = new ByteArrayInputStream(serialized.getBytes(UTF_8));
      ByteArrayOutputStream ciphertext = new ByteArrayOutputStream();

      encryptor.encrypt(plaintext, ciphertext, new FileMetadata(format("%s.json", group), UTF8));

      return ciphertext.toByteArray();
    } catch (PGPException | IOException e) {
      logger.error("Unable to backup secrets", e);
      throw new InternalServerErrorException("Unable to backup secrets, check logs for details");
    }
  }