public Map<String, IJavaField> getFields(boolean fromSuper) {
        Map<String, IJavaField> fields = new HashMap<String, IJavaField>();

        PsiClass psiClass = getPsiClass();
        if (psiClass == null) {
            return fields;
        }

        PsiField[] classFields;
        try {
            classFields = fromSuper ? getPsiClass().getAllFields() : getPsiClass().getFields();
        } catch (PsiInvalidElementAccessException ex) {
            // thrown if the class is invalid, should ignore and return an empty Map
            return fields;
        }

        for (PsiField field : classFields)
            fields.put(field.getName(), new IntellijJavaField(_module, field));

        return fields;
    }