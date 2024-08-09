@Timed @ExceptionMetered
  @POST
  @Path("request/contents")
  @Produces(APPLICATION_JSON)
  public SecretContentsResponseV2 secretContents(@Auth AutomationClient automationClient,
      @Valid SecretContentsRequestV2 request) {
    HashMap<String, String> successSecrets = new HashMap<>();
    ArrayList<String> missingSecrets = new ArrayList<>();

    // Get the contents for each secret, recording any errors
    for (String secretName : request.secrets()) {
      // Get the secret, if present
      Optional<Secret> secret = secretController.getSecretByName(secretName);

      if (!secret.isPresent()) {
        missingSecrets.add(secretName);
      } else {
        successSecrets.put(secretName, secret.get().getSecret());
      }
    }

    // Record the read in the audit log, tracking which secrets were found and not found
    Map<String, String> extraInfo = new HashMap<>();
    extraInfo.put("success_secrets", successSecrets.keySet().toString());
    extraInfo.put("missing_secrets", missingSecrets.toString());
    auditLog.recordEvent(new Event(Instant.now(), EventTag.SECRET_READCONTENT, automationClient.getName(), request.secrets().toString(), extraInfo));

    return SecretContentsResponseV2.builder()
        .successSecrets(successSecrets)
        .missingSecrets(missingSecrets)
        .build();
  }