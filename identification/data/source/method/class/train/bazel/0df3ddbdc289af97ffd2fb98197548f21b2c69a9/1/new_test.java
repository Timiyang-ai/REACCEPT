  @Test
  public void addAll_mapsTestedConverterClassesToTester() throws Exception {
    ConverterTester stringTester = new ConverterTester(Converters.StringConverter.class);
    ConverterTester intTester = new ConverterTester(Converters.IntegerConverter.class);
    ConverterTester doubleTester = new ConverterTester(Converters.DoubleConverter.class);
    ConverterTester booleanTester = new ConverterTester(Converters.BooleanConverter.class);
    ConverterTesterMap map =
        new ConverterTesterMap.Builder()
            .addAll(ImmutableList.of(stringTester, intTester, doubleTester, booleanTester))
            .build();
    assertThat(map)
        .containsExactly(
            Converters.StringConverter.class,
            stringTester,
            Converters.IntegerConverter.class,
            intTester,
            Converters.DoubleConverter.class,
            doubleTester,
            Converters.BooleanConverter.class,
            booleanTester);
  }