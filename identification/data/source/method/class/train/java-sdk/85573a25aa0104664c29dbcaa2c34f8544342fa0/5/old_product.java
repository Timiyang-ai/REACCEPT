public ServiceCall<EntityExport> getEntity(GetEntityOptions getEntityOptions) {
    Validator.notNull(getEntityOptions, "getEntityOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/entities/%s", getEntityOptions
        .workspaceId(), getEntityOptions.entity()));
    builder.query(VERSION, versionDate);
    if (getEntityOptions.export() != null) {
      builder.query("export", String.valueOf(getEntityOptions.export()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(EntityExport.class));
  }