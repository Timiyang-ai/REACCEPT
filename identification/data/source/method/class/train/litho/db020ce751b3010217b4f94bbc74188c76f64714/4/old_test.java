  @Test
  public void createOnEventMethod() {
    testHelper.getPsiClass(
        psiClasses -> {
          Assert.assertNotNull(psiClasses);
          PsiClass psiEvent = psiClasses.get(0);

          PsiMethod onEventMethod =
              OnEventGenerateUtils.createOnEventMethod(psiEvent, psiEvent, Collections.emptyList());

          Assert.assertEquals(2, onEventMethod.getParameters().length);
          PsiType contextType = (PsiType) onEventMethod.getParameters()[0].getType();
          Assert.assertEquals(
              LithoClassNames.COMPONENT_CONTEXT_CLASS_NAME, contextType.getCanonicalText());
          Assert.assertEquals("boolean", onEventMethod.getReturnType().getCanonicalText());
          Assert.assertEquals("onReturnEvent", onEventMethod.getName());

          return true;
        },
        "ReturnEvent.java");
  }