boolean allowsMultipleValues(String optionName) {
    OptionDetails optionData = transitiveOptionsMap.get(optionName);
    return (optionData == null) ? false : optionData.allowsMultiple;
  }