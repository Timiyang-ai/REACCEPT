public ServiceCall<Example> getExample(GetExampleOptions getExampleOptions) {
    Validator.notNull(getExampleOptions, "getExampleOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/workspaces/%s/intents/%s/examples/%s",
        getExampleOptions.workspaceId(), getExampleOptions.intent(), getExampleOptions.text()));
    builder.query(VERSION, versionDate);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Example.class));
  }