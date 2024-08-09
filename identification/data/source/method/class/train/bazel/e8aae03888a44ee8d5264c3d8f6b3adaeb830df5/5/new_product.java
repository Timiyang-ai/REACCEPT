@Nullable
  public Object getOptionValue(String optionName) {
    OptionDetails optionDetails = transitiveOptionsMap.get(optionName);
    return (optionDetails == null) ? null : optionDetails.value;
  }