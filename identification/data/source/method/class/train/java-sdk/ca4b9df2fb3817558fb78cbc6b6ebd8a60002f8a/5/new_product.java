public ServiceCall<Workspace> updateWorkspace(UpdateWorkspaceOptions updateWorkspaceOptions) {
    Validator.notNull(updateWorkspaceOptions, "updateWorkspaceOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/workspaces/%s", updateWorkspaceOptions
        .workspaceId()));
    builder.query(VERSION, versionDate);
    if (updateWorkspaceOptions.append() != null) {
      builder.query("append", String.valueOf(updateWorkspaceOptions.append()));
    }
    final JsonObject contentJson = new JsonObject();
    if (updateWorkspaceOptions.name() != null) {
      contentJson.addProperty("name", updateWorkspaceOptions.name());
    }
    if (updateWorkspaceOptions.description() != null) {
      contentJson.addProperty("description", updateWorkspaceOptions.description());
    }
    if (updateWorkspaceOptions.language() != null) {
      contentJson.addProperty("language", updateWorkspaceOptions.language());
    }
    if (updateWorkspaceOptions.intents() != null) {
      contentJson.add("intents", GsonSingleton.getGson().toJsonTree(updateWorkspaceOptions.intents()));
    }
    if (updateWorkspaceOptions.entities() != null) {
      contentJson.add("entities", GsonSingleton.getGson().toJsonTree(updateWorkspaceOptions.entities()));
    }
    if (updateWorkspaceOptions.dialogNodes() != null) {
      contentJson.add("dialog_nodes", GsonSingleton.getGson().toJsonTree(updateWorkspaceOptions.dialogNodes()));
    }
    if (updateWorkspaceOptions.counterexamples() != null) {
      contentJson.add("counterexamples", GsonSingleton.getGson().toJsonTree(updateWorkspaceOptions.counterexamples()));
    }
    if (updateWorkspaceOptions.metadata() != null) {
      contentJson.add("metadata", GsonSingleton.getGson().toJsonTree(updateWorkspaceOptions.metadata()));
    }
    if (updateWorkspaceOptions.learningOptOut() != null) {
      contentJson.addProperty("learning_opt_out", updateWorkspaceOptions.learningOptOut());
    }
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Workspace.class));
  }