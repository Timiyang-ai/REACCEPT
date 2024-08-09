  private static String expand(String template, Map<String, Object> variables) {
    RequestTemplate requestTemplate = new RequestTemplate();
    requestTemplate.uri(template);
    return requestTemplate.resolve(variables).url();
  }