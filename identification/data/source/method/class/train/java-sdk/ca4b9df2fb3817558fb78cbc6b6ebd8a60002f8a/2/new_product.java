public ServiceCall<ExampleCollection> listExamples(ListExamplesOptions listExamplesOptions) {
    Validator.notNull(listExamplesOptions, "listExamplesOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/intents/%s/examples",
        listExamplesOptions.workspaceId(), listExamplesOptions.intent()));
    builder.query(VERSION, versionDate);
    if (listExamplesOptions.pageLimit() != null) {
      builder.query("page_limit", String.valueOf(listExamplesOptions.pageLimit()));
    }
    if (listExamplesOptions.includeCount() != null) {
      builder.query("include_count", String.valueOf(listExamplesOptions.includeCount()));
    }
    if (listExamplesOptions.sort() != null) {
      builder.query("sort", listExamplesOptions.sort());
    }
    if (listExamplesOptions.cursor() != null) {
      builder.query("cursor", listExamplesOptions.cursor());
    }
    if (listExamplesOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(listExamplesOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(ExampleCollection.class));
  }