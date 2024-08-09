public ServiceCall<DialogNode> createDialogNode(CreateDialogNodeOptions createDialogNodeOptions) {
    Validator.notNull(createDialogNodeOptions, "createDialogNodeOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/workspaces/%s/dialog_nodes", createDialogNodeOptions
        .workspaceId()));
    builder.query(VERSION, versionDate);
    final JsonObject contentJson = new JsonObject();
    if (createDialogNodeOptions.parent() != null) {
      contentJson.addProperty("parent", createDialogNodeOptions.parent());
    }
    if (createDialogNodeOptions.metadata() != null) {
      contentJson.add("metadata", GsonSingleton.getGson().toJsonTree(createDialogNodeOptions.metadata()));
    }
    if (createDialogNodeOptions.description() != null) {
      contentJson.addProperty("description", createDialogNodeOptions.description());
    }
    if (createDialogNodeOptions.type() != null) {
      contentJson.addProperty("type", createDialogNodeOptions.type());
    }
    if (createDialogNodeOptions.title() != null) {
      contentJson.addProperty("title", createDialogNodeOptions.title());
    }
    if (createDialogNodeOptions.output() != null) {
      contentJson.add("output", GsonSingleton.getGson().toJsonTree(createDialogNodeOptions.output()));
    }
    if (createDialogNodeOptions.nextStep() != null) {
      contentJson.add("next_step", GsonSingleton.getGson().toJsonTree(createDialogNodeOptions.nextStep()));
    }
    if (createDialogNodeOptions.variable() != null) {
      contentJson.addProperty("variable", createDialogNodeOptions.variable());
    }
    if (createDialogNodeOptions.context() != null) {
      contentJson.add("context", GsonSingleton.getGson().toJsonTree(createDialogNodeOptions.context()));
    }
    if (createDialogNodeOptions.eventName() != null) {
      contentJson.addProperty("event_name", createDialogNodeOptions.eventName());
    }
    if (createDialogNodeOptions.previousSibling() != null) {
      contentJson.addProperty("previous_sibling", createDialogNodeOptions.previousSibling());
    }
    if (createDialogNodeOptions.conditions() != null) {
      contentJson.addProperty("conditions", createDialogNodeOptions.conditions());
    }
    if (createDialogNodeOptions.actions() != null) {
      contentJson.add("actions", GsonSingleton.getGson().toJsonTree(createDialogNodeOptions.actions()));
    }
    contentJson.addProperty("dialog_node", createDialogNodeOptions.dialogNode());
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(DialogNode.class));
  }