  @Test
  public void asPackage() {
    assertThat(MoreElements.asPackage(javaLangPackageElement))
        .isEqualTo(javaLangPackageElement);
  }