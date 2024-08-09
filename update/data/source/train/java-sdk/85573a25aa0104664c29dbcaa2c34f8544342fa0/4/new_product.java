public ServiceCall<Counterexample> getCounterexample(GetCounterexampleOptions getCounterexampleOptions) {
    Validator.notNull(getCounterexampleOptions, "getCounterexampleOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/counterexamples/%s",
        getCounterexampleOptions.workspaceId(), getCounterexampleOptions.text()));
    builder.query(VERSION, versionDate);
    if (getCounterexampleOptions.includeAudit() != null) {
      builder.query("include_audit", String.valueOf(getCounterexampleOptions.includeAudit()));
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Counterexample.class));
  }