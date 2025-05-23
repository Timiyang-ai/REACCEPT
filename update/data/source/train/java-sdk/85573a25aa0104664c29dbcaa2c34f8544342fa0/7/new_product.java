public ServiceCall<ValueExport> getValue(GetValueOptions getValueOptions) {
    Validator.notNull(getValueOptions, "getValueOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/entities/%s/values/%s", getValueOptions
        .workspaceId(), getValueOptions.entity(), getValueOptions.value()));
    builder.query(VERSION, versionDate);
    if (getValueOptions.export() != null) {
      builder.query("export", String.valueOf(getValueOptions.export()));
    }
    if (getValueOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(getValueOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(ValueExport.class));
  }