@Nullable
  public SelectRestriction getSelectRestriction(String optionName) {
    OptionDetails optionDetails = transitiveNativeOptionsMap.get(optionName);
    return optionDetails == null ? null : optionDetails.selectRestriction;
  }