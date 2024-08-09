@SuppressWarnings({"unchecked" })
  public Context combine(final Map<String, ?> model) {
    Map<String, Object> map = (Map<String, Object>) extendedContext.model;
    map.putAll(model);
    return this;
  }