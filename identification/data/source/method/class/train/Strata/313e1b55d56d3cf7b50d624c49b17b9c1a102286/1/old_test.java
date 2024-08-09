  @Test
  public void getSingleValue() {
    MarketDataBox<Integer> box = MarketDataBox.ofScenarioValues(27, 28, 29);
    assertThatIllegalStateException()
        .isThrownBy(box::getSingleValue)
        .withMessage("This box does not contain a single value");
  }