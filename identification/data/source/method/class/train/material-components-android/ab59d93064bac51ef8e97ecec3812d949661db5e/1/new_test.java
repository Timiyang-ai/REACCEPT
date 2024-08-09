  @Test
  public void setCornerRadius_defaultDoesNotChange() {
    shapeAppearance = ShapeAppearanceModel.builder().setAllCornerSizes(DEFAULT_CORNER_SIZE).build();
    ShapeAppearanceModel largeCornerShape =
        shapeAppearance.toBuilder().setAllCornerSizes(LARGE_CORNER_SIZE).build();

    assertCornerSize(shapeAppearance, DEFAULT_CORNER_SIZE);
    assertCornerSize(largeCornerShape, LARGE_CORNER_SIZE);
  }