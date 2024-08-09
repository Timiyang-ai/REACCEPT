public ServiceCall<Synonym> getSynonym(GetSynonymOptions getSynonymOptions) {
    Validator.notNull(getSynonymOptions, "getSynonymOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/entities/%s/values/%s/synonyms/%s",
        getSynonymOptions.workspaceId(), getSynonymOptions.entity(), getSynonymOptions.value(), getSynonymOptions
            .synonym()));
    builder.query(VERSION, versionDate);
    if (getSynonymOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(getSynonymOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Synonym.class));
  }