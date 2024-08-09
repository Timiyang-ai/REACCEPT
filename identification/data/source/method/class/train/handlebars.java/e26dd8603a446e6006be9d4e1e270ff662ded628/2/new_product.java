@Override
  protected void renderMergedTemplateModel(final Map<String, Object> model,
      final HttpServletRequest request, final HttpServletResponse response)
      throws Exception {
    Context context = Context.newBuilder(model)
        .resolver(valueResolvers)
        .build();
    try {
      template.apply(context, response.getWriter());
    } finally {
      context.destroy();
    }
  }