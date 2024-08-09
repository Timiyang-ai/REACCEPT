Stream<PsiDirectory> provideGeneratedComponentDirs(
      String specDirPath, String specQualifiedName, Project project) {
    String specPackageName = StringUtil.getPackageName(specQualifiedName);
    VirtualFile baseDir = project.getBaseDir();
    PsiManager psiManager = PsiManager.getInstance(project);
    PsiClass generatedClass = LithoPluginUtils.findGeneratedClass(specQualifiedName, project);
    return Stream.concat(
        getContainingDirectory(generatedClass),
        provideGeneratedComponentDirs(
            providers, specDirPath, specPackageName, baseDir, psiManager));
  }