@SuppressWarnings({"unchecked" })
  public Context combine(final String name, final Object model) {
    Map<String, Object> map = (Map<String, Object>) extendedContext.model;
    map.put(name, model);
    return this;
  }