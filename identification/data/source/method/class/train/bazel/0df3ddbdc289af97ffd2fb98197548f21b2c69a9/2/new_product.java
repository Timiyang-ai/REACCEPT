public ConverterTester addEqualityGroup(String... inputs) {
    ImmutableList.Builder<WrappedItem> wrapped = ImmutableList.builder();
    ImmutableList<String> inputList = ImmutableList.copyOf(inputs);
    inputLists.add(inputList);
    for (String input : inputList) {
      testedInputs.add(input);
      try {
        wrapped.add(new WrappedItem(input, converter.convert(input)));
      } catch (OptionsParsingException ex) {
        throw new AssertionError("Failed to parse input: \"" + input + "\"", ex);
      }
    }
    tester.addEqualityGroup(wrapped.build().toArray());
    return this;
  }