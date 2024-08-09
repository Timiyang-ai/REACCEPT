public ServiceCall<IntentCollection> listIntents(ListIntentsOptions listIntentsOptions) {
    Validator.notNull(listIntentsOptions, "listIntentsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/intents", listIntentsOptions
        .workspaceId()));
    builder.query(VERSION, versionDate);
    if (listIntentsOptions.export() != null) {
      builder.query("export", String.valueOf(listIntentsOptions.export()));
    }
    if (listIntentsOptions.pageLimit() != null) {
      builder.query("page_limit", String.valueOf(listIntentsOptions.pageLimit()));
    }
    if (listIntentsOptions.includeCount() != null) {
      builder.query("include_count", String.valueOf(listIntentsOptions.includeCount()));
    }
    if (listIntentsOptions.sort() != null) {
      builder.query("sort", listIntentsOptions.sort());
    }
    if (listIntentsOptions.cursor() != null) {
      builder.query("cursor", listIntentsOptions.cursor());
    }
    if (listIntentsOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(listIntentsOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(IntentCollection.class));
  }