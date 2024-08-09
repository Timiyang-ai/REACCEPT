public ServiceCall<IntentExport> getIntent(GetIntentOptions getIntentOptions) {
    Validator.notNull(getIntentOptions, "getIntentOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/intents/%s", getIntentOptions
        .workspaceId(), getIntentOptions.intent()));
    builder.query(VERSION, versionDate);
    if (getIntentOptions.export() != null) {
      builder.query("export", String.valueOf(getIntentOptions.export()));
    }
    if (getIntentOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(getIntentOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(IntentExport.class));
  }