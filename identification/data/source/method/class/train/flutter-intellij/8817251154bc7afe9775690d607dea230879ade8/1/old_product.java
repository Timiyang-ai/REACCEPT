public static boolean isMainFunctionDeclaration(@Nullable PsiElement element) {
    if (!(element instanceof DartFunctionDeclarationWithBodyOrNative)) return false;
    final String functionName = ((DartFunctionDeclarationWithBodyOrNative)element).getComponentName().getId().getText();
    return Objects.equals(functionName, "main");
  }