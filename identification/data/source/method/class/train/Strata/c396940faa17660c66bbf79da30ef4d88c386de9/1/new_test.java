  @Test
  public void test_combinedWith() {
    PortfolioItemInfo base = PortfolioItemInfo.empty()
        .withId(ID)
        .withAttribute(AttributeType.DESCRIPTION, "A");
    PositionInfo other = PositionInfo.empty()
        .withId(ID2)
        .withAttribute(AttributeType.DESCRIPTION, "B")
        .withAttribute(AttributeType.NAME, "B");
    PortfolioItemInfo test = base.combinedWith(other);
    assertThat(test.getId()).hasValue(ID);
    assertThat(test.getAttributeTypes()).containsOnly(AttributeType.DESCRIPTION, AttributeType.NAME);
  }