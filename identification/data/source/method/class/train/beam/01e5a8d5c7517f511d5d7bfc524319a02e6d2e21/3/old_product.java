String registerCoder(Coder<?> coder) throws IOException {
    String existing = coderIds.get(Equivalence.identity().wrap(coder));
    if (existing != null) {
      return existing;
    }
    String baseName = NameUtils.approximateSimpleName(coder);
    String name = uniqify(baseName, coderIds.values());
    coderIds.put(Equivalence.identity().wrap(coder), name);
    RunnerApi.Coder coderProto = CoderTranslation.toProto(coder, this);
    componentsBuilder.putCoders(name, coderProto);
    return name;
  }