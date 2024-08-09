synchronized <T extends PipelineOptions> T cloneAs(Object proxy, Class<T> iface) {
    PipelineOptions clonedOptions;
    try {
      clonedOptions = MAPPER.readValue(MAPPER.writeValueAsBytes(proxy), PipelineOptions.class);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to serialize the pipeline options to JSON.", e);
    }
    for (Class<? extends PipelineOptions> knownIface : knownInterfaces) {
      clonedOptions.as(knownIface);
    }
    return clonedOptions.as(iface);
  }