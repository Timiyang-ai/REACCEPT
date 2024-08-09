@SuppressWarnings({"unchecked" })
  private void combine(final Map<String, Object> model) {
    Map<String, Object> map = (Map<String, Object>) extendedContext.model;
    map.putAll(model);
  }