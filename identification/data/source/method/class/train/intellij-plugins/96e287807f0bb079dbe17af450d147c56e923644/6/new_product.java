@Override
    public IJavaField createField(@NotNull String name, IJavaClassType type, boolean isPrivate, boolean changeNameToReflectIdeSettings) {
        String fieldName;
        if (changeNameToReflectIdeSettings) {
            fieldName = JavaCodeStyleManager.getInstance(_module.getProject()).propertyNameToVariableName(StringUtil.decapitalize(name), VariableKind.FIELD);
        } else {
            fieldName = name;
        }

        try {
            PsiField field = JavaPsiFacade.getInstance(_module.getProject()).getElementFactory().
                    createField(fieldName, JavaPsiFacade.getInstance(_module.getProject()).getElementFactory().createType(((IntellijJavaClassType) type).getPsiClass()));

            field.getModifierList().setModifierProperty(PsiModifier.PRIVATE, isPrivate);

            return new IntellijJavaField(_module, field);
        } catch (Throwable ex) {
            _logger.error(ex);

            return null;
        }
    }