@SuppressWarnings({"unchecked" })
  private void combine(final String name, final Object model) {
    checkNotNull(name, "The variable's name is required.");
    Map<String, Object> map = (Map<String, Object>) extendedContext.model;
    map.put(name, model);
  }