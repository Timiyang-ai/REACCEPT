  @Test
  public void test_combinedWith_other_other_noClash() {
    ReferenceData test = REF_DATA1.combinedWith(REF_DATA2);
    assertThat(test.getValue(ID1)).isEqualTo(VAL1);
    assertThat(test.getValue(ID2)).isEqualTo(VAL2);
  }