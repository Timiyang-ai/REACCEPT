public boolean allowsMultipleValues(String optionName) {
    OptionDetails optionDetails = transitiveOptionsMap.get(optionName);
    return optionDetails != null && optionDetails.allowsMultiple;
  }