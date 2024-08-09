@Override
  protected void renderMergedTemplateModel(final Map<String, Object> model,
      final HttpServletRequest request, final HttpServletResponse response)
      throws Exception {
    template.apply(model, response.getWriter());
  }