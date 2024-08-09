private Str system(final QueryContext ctx) {
    return Str.get(delete(Info.info(ctx.resource.context), '\r'));
  }