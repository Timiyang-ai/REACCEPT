@Override
  public boolean hasDefaultConstructor() {
    return PsiUtil.hasDefaultConstructor(getPsiClass());
  }