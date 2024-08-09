  @Test
  public void isMainFunctionDeclaration() throws Exception {
    run(() -> {
      final PsiElement mainIdentifier = setUpDartElement("main() { test('my first test', () {} ); }", "main", LeafPsiElement.class);
      final PsiElement main =
        PsiTreeUtil.findFirstParent(mainIdentifier, element -> element instanceof DartFunctionDeclarationWithBodyOrNative);
      assertTrue(DartSyntax.isMainFunctionDeclaration(main));
    });
  }