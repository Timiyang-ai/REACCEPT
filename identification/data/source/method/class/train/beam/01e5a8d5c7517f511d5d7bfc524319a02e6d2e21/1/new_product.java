public String registerCoder(Coder<?> coder) throws IOException {
    String existing = coderIds.get(coder);
    if (existing != null) {
      return existing;
    }
    String baseName = NameUtils.approximateSimpleName(coder);
    String name = uniqify(baseName, coderIds.values());
    coderIds.put(coder, name);
    RunnerApi.Coder coderProto = CoderTranslation.toProto(coder, this);
    componentsBuilder.putCoders(name, coderProto);
    return name;
  }