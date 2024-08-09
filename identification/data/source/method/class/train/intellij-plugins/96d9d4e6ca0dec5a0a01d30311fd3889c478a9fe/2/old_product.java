public static boolean isModuleNode(AnActionEvent event) {
    final DataContext dataContext = event.getDataContext();
    final Project project = (Project)dataContext.getData(DataConstantsEx.PROJECT);
    final Module module = (Module)dataContext.getData(DataConstantsEx.MODULE_CONTEXT);

    return project != null && module != null;
  }