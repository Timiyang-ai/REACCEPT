@Override
  protected void renderMergedTemplateModel(final Map<String, Object> model,
      final HttpServletRequest request, final HttpServletResponse response)
      throws Exception {
    Context context = Context.newBuilder(model)
      .resolver(valueResolvers)
      .build();
    template.apply(context, response.getWriter());
  }