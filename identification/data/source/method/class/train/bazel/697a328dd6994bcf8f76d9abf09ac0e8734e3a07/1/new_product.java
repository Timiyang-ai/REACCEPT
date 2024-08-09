public boolean allowsMultipleValues(String optionName) {
    OptionDetails optionDetails = transitiveNativeOptionsMap.get(optionName);
    return optionDetails != null && optionDetails.allowsMultiple;
  }