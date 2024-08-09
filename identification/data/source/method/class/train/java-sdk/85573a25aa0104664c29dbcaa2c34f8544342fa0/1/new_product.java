public ServiceCall<DialogNodeCollection> listDialogNodes(ListDialogNodesOptions listDialogNodesOptions) {
    Validator.notNull(listDialogNodesOptions, "listDialogNodesOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/dialog_nodes", listDialogNodesOptions
        .workspaceId()));
    builder.query(VERSION, versionDate);
    if (listDialogNodesOptions.pageLimit() != null) {
      builder.query("page_limit", String.valueOf(listDialogNodesOptions.pageLimit()));
    }
    if (listDialogNodesOptions.includeCount() != null) {
      builder.query("include_count", String.valueOf(listDialogNodesOptions.includeCount()));
    }
    if (listDialogNodesOptions.sort() != null) {
      builder.query("sort", listDialogNodesOptions.sort());
    }
    if (listDialogNodesOptions.cursor() != null) {
      builder.query("cursor", listDialogNodesOptions.cursor());
    }
    if (listDialogNodesOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(listDialogNodesOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(DialogNodeCollection.class));
  }