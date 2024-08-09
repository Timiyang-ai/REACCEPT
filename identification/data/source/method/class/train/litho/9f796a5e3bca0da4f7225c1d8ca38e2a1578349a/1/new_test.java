  @Test
  public void guessClassName() {
    Assert.assertEquals("T", PsiTypeUtils.guessClassName("T").reflectionName());
    Assert.assertEquals("com.Hello", PsiTypeUtils.guessClassName("com.Hello").reflectionName());
  }