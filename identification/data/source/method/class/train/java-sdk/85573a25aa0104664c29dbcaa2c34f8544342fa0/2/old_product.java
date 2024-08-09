public ServiceCall<Counterexample> getCounterexample(GetCounterexampleOptions getCounterexampleOptions) {
    Validator.notNull(getCounterexampleOptions, "getCounterexampleOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/counterexamples/%s",
        getCounterexampleOptions.workspaceId(), getCounterexampleOptions.text()));
    builder.query(VERSION, versionDate);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Counterexample.class));
  }