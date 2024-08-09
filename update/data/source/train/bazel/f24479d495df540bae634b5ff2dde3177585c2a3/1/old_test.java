@SuppressWarnings("unchecked")
  @Test
  public void testSelectableConvert() throws Exception {
    Object nativeInput = Arrays.asList("//a:a1", "//a:a2");
    Object selectableInput =
        SelectorList.of(new SelectorValue(ImmutableMap.of(
            "//conditions:a", nativeInput,
            BuildType.Selector.DEFAULT_CONDITION_KEY, nativeInput), ""));
    List<Label> expectedLabels =
        ImmutableList.of(Label.create("@//a", "a1"), Label.create("@//a", "a2"));

    // Conversion to direct type:
    Object converted = BuildType
        .selectableConvert(BuildType.LABEL_LIST, nativeInput, null, currentRule);
    assertThat(converted instanceof List<?>).isTrue();
    assertThat((List<Label>) converted).containsExactlyElementsIn(expectedLabels);

    // Conversion to selectable type:
    converted = BuildType
        .selectableConvert(BuildType.LABEL_LIST, selectableInput, null, currentRule);
    BuildType.SelectorList<?> selectorList = (BuildType.SelectorList<?>) converted;
    assertThat(((Selector<Label>) selectorList.getSelectors().get(0)).getEntries().entrySet())
        .containsExactlyElementsIn(
            ImmutableMap.of(
                    Label.parseAbsolute("//conditions:a"),
                    expectedLabels,
                    Label.parseAbsolute(BuildType.Selector.DEFAULT_CONDITION_KEY),
                    expectedLabels)
                .entrySet());
  }