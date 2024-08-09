  @Test
  public void test_requirements() {
    FixedIborSwapCurveNode test = FixedIborSwapCurveNode.of(TEMPLATE, QUOTE_ID, SPREAD);
    Set<ObservableId> set = test.requirements();
    Iterator<ObservableId> itr = set.iterator();
    assertThat(itr.next()).isEqualTo(QUOTE_ID);
    assertThat(itr.hasNext()).isFalse();
  }