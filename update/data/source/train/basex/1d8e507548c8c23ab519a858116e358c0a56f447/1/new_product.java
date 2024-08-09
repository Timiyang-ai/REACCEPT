private Str system(final QueryContext ctx) {
    return Str.get(delete(Info.info(ctx.context), '\r'));
  }