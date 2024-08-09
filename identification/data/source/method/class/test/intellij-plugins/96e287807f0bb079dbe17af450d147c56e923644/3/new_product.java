@Override
  public boolean isPublic() {
    return getPsiClass().getModifierList().hasModifierProperty(PsiModifier.PUBLIC);
  }