  @Test
  public void addError() {
    String message = "test message";
    PsiElement element = Mockito.mock(PsiElement.class);
    TestHolder holder = new TestHolder();
    AnnotatorUtils.addError(holder, new SpecModelValidationError(element, message));

    assertEquals(1, holder.errorMessages.size());
    assertEquals(message, holder.errorMessages.get(0));
    assertEquals(1, holder.errorElements.size());
    assertEquals(element, holder.errorElements.get(0));
  }