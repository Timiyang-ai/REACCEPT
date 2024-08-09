public ServiceCall<Workspace> createWorkspace(CreateWorkspaceOptions createWorkspaceOptions) {
    RequestBuilder builder = RequestBuilder.post("/v1/workspaces");
    builder.query(VERSION, versionDate);
    if (createWorkspaceOptions != null) {
      final JsonObject contentJson = new JsonObject();
      if (createWorkspaceOptions.metadata() != null) {
        contentJson.add("metadata", GsonSingleton.getGson().toJsonTree(createWorkspaceOptions.metadata()));
      }
      if (createWorkspaceOptions.intents() != null) {
        contentJson.add("intents", GsonSingleton.getGson().toJsonTree(createWorkspaceOptions.intents()));
      }
      if (createWorkspaceOptions.entities() != null) {
        contentJson.add("entities", GsonSingleton.getGson().toJsonTree(createWorkspaceOptions.entities()));
      }
      if (createWorkspaceOptions.name() != null) {
        contentJson.addProperty("name", createWorkspaceOptions.name());
      }
      if (createWorkspaceOptions.counterexamples() != null) {
        contentJson.add("counterexamples", GsonSingleton.getGson().toJsonTree(createWorkspaceOptions
            .counterexamples()));
      }
      if (createWorkspaceOptions.description() != null) {
        contentJson.addProperty("description", createWorkspaceOptions.description());
      }
      if (createWorkspaceOptions.language() != null) {
        contentJson.addProperty("language", createWorkspaceOptions.language());
      }
      if (createWorkspaceOptions.dialogNodes() != null) {
        contentJson.add("dialog_nodes", GsonSingleton.getGson().toJsonTree(createWorkspaceOptions.dialogNodes()));
      }
      builder.bodyJson(contentJson);
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Workspace.class));
  }