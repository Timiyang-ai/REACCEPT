public ServiceCall<DialogNode> updateDialogNode(UpdateDialogNodeOptions updateDialogNodeOptions) {
    Validator.notNull(updateDialogNodeOptions, "updateDialogNodeOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/workspaces/%s/dialog_nodes/%s",
            updateDialogNodeOptions.workspaceId(), updateDialogNodeOptions.dialogNode()));
    builder.query(VERSION, versionDate);
    final JsonObject contentJson = new JsonObject();
    if (updateDialogNodeOptions.nodeType() != null) {
      contentJson.addProperty("type", updateDialogNodeOptions.nodeType());
    }
    if (updateDialogNodeOptions.newActions() != null) {
      contentJson.add("actions", GsonSingleton.getGson().toJsonTree(updateDialogNodeOptions.newActions()));
    }
    if (updateDialogNodeOptions.newConditions() != null) {
      contentJson.addProperty("conditions", updateDialogNodeOptions.newConditions());
    }
    if (updateDialogNodeOptions.newPreviousSibling() != null) {
      contentJson.addProperty("previous_sibling", updateDialogNodeOptions.newPreviousSibling());
    }
    if (updateDialogNodeOptions.newContext() != null) {
      contentJson.add("context", GsonSingleton.getGson().toJsonTree(updateDialogNodeOptions.newContext()));
    }
    if (updateDialogNodeOptions.newVariable() != null) {
      contentJson.addProperty("variable", updateDialogNodeOptions.newVariable());
    }
    if (updateDialogNodeOptions.newMetadata() != null) {
      contentJson.add("metadata", GsonSingleton.getGson().toJsonTree(updateDialogNodeOptions.newMetadata()));
    }
    if (updateDialogNodeOptions.newTitle() != null) {
      contentJson.addProperty("title", updateDialogNodeOptions.newTitle());
    }
    if (updateDialogNodeOptions.newDescription() != null) {
      contentJson.addProperty("description", updateDialogNodeOptions.newDescription());
    }
    if (updateDialogNodeOptions.newEventName() != null) {
      contentJson.addProperty("event_name", updateDialogNodeOptions.newEventName());
    }
    if (updateDialogNodeOptions.newNextStep() != null) {
      contentJson.add("next_step", GsonSingleton.getGson().toJsonTree(updateDialogNodeOptions.newNextStep()));
    }
    if (updateDialogNodeOptions.newOutput() != null) {
      contentJson.add("output", GsonSingleton.getGson().toJsonTree(updateDialogNodeOptions.newOutput()));
    }
    if (updateDialogNodeOptions.newParent() != null) {
      contentJson.addProperty("parent", updateDialogNodeOptions.newParent());
    }
    contentJson.addProperty("dialog_node", updateDialogNodeOptions.newDialogNode());
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(DialogNode.class));
  }