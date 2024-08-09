@Override
    public boolean isPrivate() {
        return _psiField.getModifierList().hasModifierProperty(PsiModifier.PRIVATE);
    }