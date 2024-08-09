static String route(ContainerRequest request) {
    ExtendedUriInfo uriInfo = request.getUriInfo();
    List<UriTemplate> templates = uriInfo.getMatchedTemplates();
    int templateCount = templates.size();
    if (templateCount == 0) return "";
    assert templateCount % 2 == 0 : "expected matched templates to be resource/method pairs";
    StringBuilder builder = null; // don't allocate unless you need it!
    String basePath = uriInfo.getBaseUri().getPath();
    String result = null;
    if (!"/".equals(basePath)) { // skip empty base paths
      result = basePath;
    }
    for (int i = templateCount - 1; i >= 0; i--) {
      String template = templates.get(i).getTemplate();
      if ("/".equals(template)) continue; // skip allocation
      if (builder != null) {
        builder.append(template);
      } else if (result != null) {
        builder = new StringBuilder(result).append(template);
        result = null;
      } else {
        result = template;
      }
    }
    return result != null ? result : builder != null ? builder.toString() : "";
  }