  @Test
  public void extractFields() {
    testHelper.getPsiClass(
        psiClasses -> {
          Assert.assertNotNull(psiClasses);
          PsiClass psiClass = psiClasses.get(0);

          ImmutableList<FieldModel> fieldModels = PsiFieldsExtractor.extractFields(psiClass);
          FieldsExtractorTestHelper.fieldExtraction(fieldModels);
          return true;
        },
        "TwoFieldsClass.java");
  }