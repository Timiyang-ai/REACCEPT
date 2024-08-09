public boolean isPublic() {
    return getPsiClass().getModifierList().hasExplicitModifier(PsiModifier.PUBLIC);
  }