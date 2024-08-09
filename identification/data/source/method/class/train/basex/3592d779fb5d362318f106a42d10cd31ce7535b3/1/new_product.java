public void delete() {
    final String name = "SHOW" + view.getName().toUpperCase(Locale.ENGLISH);
    view.gui.gopts.set((BooleanOption) view.gui.gopts.option(name), false);
    view.gui.layoutViews();
  }