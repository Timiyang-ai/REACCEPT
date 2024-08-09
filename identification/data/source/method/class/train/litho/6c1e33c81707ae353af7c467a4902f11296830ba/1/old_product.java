Stream<PsiDirectory> provideGeneratedComponentDirs(
      String specDirPath, String specPackageName, Project project) {
    VirtualFile baseDir = project.getBaseDir();
    PsiManager psiManager = PsiManager.getInstance(project);
    return provideGeneratedComponentDirs(
        providers, specDirPath, specPackageName, baseDir, psiManager);
  }