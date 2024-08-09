  @Test
  public void test_withNode_atStart_withMetadata() {
    InterpolatedNodalCurve base = InterpolatedNodalCurve.of(METADATA_ENTRIES, XVALUES, YVALUES, INTERPOLATOR);
    LabelDateParameterMetadata item = LabelDateParameterMetadata.of(date(2015, 6, 30), TNR_1Y);
    InterpolatedNodalCurve test = base.withNode(0.5d, 4d, item);
    List<ParameterMetadata> list = new ArrayList<>();
    list.add(item);
    list.addAll(ParameterMetadata.listOfEmpty(SIZE));
    assertThat(test.getName()).isEqualTo(CURVE_NAME);
    assertThat(test.getParameterCount()).isEqualTo(SIZE + 1);
    assertThat(test.getMetadata()).isEqualTo(METADATA_ENTRIES.withParameterMetadata(list));
    assertThat(test.getXValues()).isEqualTo(DoubleArray.of(0.5d, 1d, 2d, 3d));
    assertThat(test.getYValues()).isEqualTo(DoubleArray.of(4d, 5d, 7d, 8d));
  }