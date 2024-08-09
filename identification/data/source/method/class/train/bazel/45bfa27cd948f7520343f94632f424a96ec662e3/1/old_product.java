@Nullable
  public SelectRestriction getSelectRestriction(String optionName) {
    OptionDetails optionDetails = transitiveOptionsMap.get(optionName);
    return optionDetails == null ? null : optionDetails.selectRestriction;
  }