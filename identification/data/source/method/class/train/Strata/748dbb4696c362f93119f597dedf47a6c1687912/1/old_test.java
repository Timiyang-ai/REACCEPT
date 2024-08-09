  @Test
  public void test_compareTo() {
    LocalDateDoublePoint a = LocalDateDoublePoint.of(DATE_2012_06_29, 1d);
    LocalDateDoublePoint b = LocalDateDoublePoint.of(DATE_2012_06_30, 1d);
    LocalDateDoublePoint c = LocalDateDoublePoint.of(DATE_2012_07_01, 1d);

    List<LocalDateDoublePoint> list = new ArrayList<>();
    list.add(a);
    list.add(b);
    list.add(c);
    list.sort(Comparator.naturalOrder());
    assertThat(list).containsExactly(a, b, c);
    list.sort(Comparator.reverseOrder());
    assertThat(list).containsExactly(c, b, a);
  }