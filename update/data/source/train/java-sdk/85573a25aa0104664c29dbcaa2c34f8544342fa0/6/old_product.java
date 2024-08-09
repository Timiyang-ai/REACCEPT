public ServiceCall<DialogNode> getDialogNode(GetDialogNodeOptions getDialogNodeOptions) {
    Validator.notNull(getDialogNodeOptions, "getDialogNodeOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/dialog_nodes/%s", getDialogNodeOptions
        .workspaceId(), getDialogNodeOptions.dialogNode()));
    builder.query(VERSION, versionDate);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(DialogNode.class));
  }