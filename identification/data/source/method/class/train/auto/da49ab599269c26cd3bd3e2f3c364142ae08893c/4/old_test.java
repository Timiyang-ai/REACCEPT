  @Test
  public void asExecutable() {
    for (Element methodElement : ElementFilter.methodsIn(stringElement.getEnclosedElements())) {
      assertThat(MoreElements.asExecutable(methodElement)).isEqualTo(methodElement);
    }
    for (Element methodElement
        : ElementFilter.constructorsIn(stringElement.getEnclosedElements())) {
      assertThat(MoreElements.asExecutable(methodElement)).isEqualTo(methodElement);
    }
  }