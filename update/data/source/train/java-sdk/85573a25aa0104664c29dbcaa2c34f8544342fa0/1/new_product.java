public ServiceCall<EntityCollection> listEntities(ListEntitiesOptions listEntitiesOptions) {
    Validator.notNull(listEntitiesOptions, "listEntitiesOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/entities", listEntitiesOptions
        .workspaceId()));
    builder.query(VERSION, versionDate);
    if (listEntitiesOptions.export() != null) {
      builder.query("export", String.valueOf(listEntitiesOptions.export()));
    }
    if (listEntitiesOptions.pageLimit() != null) {
      builder.query("page_limit", String.valueOf(listEntitiesOptions.pageLimit()));
    }
    if (listEntitiesOptions.includeCount() != null) {
      builder.query("include_count", String.valueOf(listEntitiesOptions.includeCount()));
    }
    if (listEntitiesOptions.sort() != null) {
      builder.query("sort", listEntitiesOptions.sort());
    }
    if (listEntitiesOptions.cursor() != null) {
      builder.query("cursor", listEntitiesOptions.cursor());
    }
    if (listEntitiesOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(listEntitiesOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(EntityCollection.class));
  }