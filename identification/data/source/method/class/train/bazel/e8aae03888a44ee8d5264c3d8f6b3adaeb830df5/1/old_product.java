@Nullable
  public Class<? extends FragmentOptions> getOptionClass(String optionName) {
    OptionDetails optionDetails = transitiveOptionsMap.get(optionName);
    return optionDetails == null ? null : optionDetails.optionsClass;
  }