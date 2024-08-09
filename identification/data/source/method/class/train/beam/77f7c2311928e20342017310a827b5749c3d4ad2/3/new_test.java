  private <T extends PipelineOptions> T serializeDeserialize(Class<T> kls, PipelineOptions options)
      throws Exception {
    String value = MAPPER.writeValueAsString(options);
    return MAPPER.readValue(value, PipelineOptions.class).as(kls);
  }