@Nullable
  public Object getOptionValue(String optionName) {
    OptionDetails optionDetails = transitiveNativeOptionsMap.get(optionName);
    return (optionDetails == null) ? null : optionDetails.value;
  }