public IJavaClassType getContainingClass() {
        return new IntellijJavaClassType(_module, _psiMethod.getContainingClass().getContainingFile());
    }