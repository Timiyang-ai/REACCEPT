@Override
    @Nullable
    public IJavaType getType() {
        if (_psiField.getType() instanceof PsiClassType) {
            return IdeaUtils.createJavaTypeFromPsiType(_module, _psiField.getType());
        }

        if (_psiField.getType() instanceof PsiPrimitiveType) {
            return IdeaUtils.createJavaTypeFromPsiType(_module, _psiField.getType());
        }

        if (_psiField.getType() instanceof PsiArrayType)
            return IdeaUtils.createJavaTypeFromPsiType(_module, _psiField.getType());

        return null;
    }