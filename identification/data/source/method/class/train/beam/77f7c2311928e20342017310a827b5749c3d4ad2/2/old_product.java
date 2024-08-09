synchronized <T extends PipelineOptions> T cloneAs(Object proxy, Class<T> iface) {
    try {
      return MAPPER.readValue(MAPPER.writeValueAsBytes(proxy), PipelineOptions.class).as(iface);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to serialize the pipeline options to JSON.", e);
    }
  }