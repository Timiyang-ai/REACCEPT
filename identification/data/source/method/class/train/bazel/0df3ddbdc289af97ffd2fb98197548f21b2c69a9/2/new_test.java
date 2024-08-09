  @Test
  public void addEqualityGroup_throwsIfConversionFails() throws Exception {
    ConverterTester tester =
        new ConverterTester(ThrowingConverter.class)
            .addEqualityGroup("okay")
            .addEqualityGroup("also okay", "pretty fine");
    try {
      tester.addEqualityGroup("wrong");
    } catch (AssertionError expected) {
      assertThat(expected).hasMessageThat().contains("\"wrong\"");
      assertThat(expected).hasCauseThat().hasMessageThat().contains("HOW DARE YOU");
      return;
    }
    fail("expected addEqualityGroup to fail");
  }