@Nullable
  public Class<? extends FragmentOptions> getOptionClass(String optionName) {
    OptionDetails optionDetails = transitiveNativeOptionsMap.get(optionName);
    return optionDetails == null ? null : optionDetails.optionsClass;
  }