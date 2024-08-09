public static boolean isModuleNode(AnActionEvent event) {
    final Project project = event.getData(CommonDataKeys.PROJECT);
    final Module module = event.getData(LangDataKeys.MODULE_CONTEXT);

    return project != null && module != null;
  }