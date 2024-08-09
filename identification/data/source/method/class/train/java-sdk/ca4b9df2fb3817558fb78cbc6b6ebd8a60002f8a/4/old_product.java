public ServiceCall<WorkspaceExport> getWorkspace(GetWorkspaceOptions getWorkspaceOptions) {
    Validator.notNull(getWorkspaceOptions, "getWorkspaceOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s", getWorkspaceOptions.workspaceId()));
    builder.query(VERSION, versionDate);
    if (getWorkspaceOptions.export() != null) {
      builder.query("export", String.valueOf(getWorkspaceOptions.export()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(WorkspaceExport.class));
  }