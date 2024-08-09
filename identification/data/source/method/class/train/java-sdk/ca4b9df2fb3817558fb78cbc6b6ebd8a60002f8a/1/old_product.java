public ServiceCall<CounterexampleCollection> listCounterexamples(
      ListCounterexamplesOptions listCounterexamplesOptions) {
    Validator.notNull(listCounterexamplesOptions, "listCounterexamplesOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/counterexamples",
        listCounterexamplesOptions.workspaceId()));
    builder.query(VERSION, versionDate);
    if (listCounterexamplesOptions.pageLimit() != null) {
      builder.query("page_limit", String.valueOf(listCounterexamplesOptions.pageLimit()));
    }
    if (listCounterexamplesOptions.includeCount() != null) {
      builder.query("include_count", String.valueOf(listCounterexamplesOptions.includeCount()));
    }
    if (listCounterexamplesOptions.sort() != null) {
      builder.query("sort", listCounterexamplesOptions.sort());
    }
    if (listCounterexamplesOptions.cursor() != null) {
      builder.query("cursor", listCounterexamplesOptions.cursor());
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(CounterexampleCollection.class));
  }