public void createDB(final String name) {
    final CreateDB c = new CreateDB(name, "<" + DeepFS.S_FSML + "/>");
    if(!c.run(ctx)) Util.notexpected(c.info());
    ctx.data.meta.deepfs = true;
  }