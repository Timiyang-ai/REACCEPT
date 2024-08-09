@Override
  public IResource getFile() {
    VirtualFile vf = VirtualFileManager.getInstance().findFileByUrl(_classFilePath);

    if (vf != null) {
      return new IntellijResource(PsiManager.getInstance(_module.getProject()).findFile(vf));
    }
    else {
      return null;
    }
  }