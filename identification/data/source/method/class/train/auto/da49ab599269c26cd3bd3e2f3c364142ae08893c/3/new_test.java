  @Test
  public void asVariable() {
    for (Element variableElement : ElementFilter.fieldsIn(stringElement.getEnclosedElements())) {
      assertThat(MoreElements.asVariable(variableElement)).isEqualTo(variableElement);
    }
  }