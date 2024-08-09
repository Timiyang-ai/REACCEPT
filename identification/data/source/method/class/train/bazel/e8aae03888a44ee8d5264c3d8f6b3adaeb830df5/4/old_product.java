public Class<? extends OptionsBase> getOptionClass(String optionName) {
    OptionDetails optionData = transitiveOptionsMap.get(optionName);
    return optionData == null ? null : optionData.optionsClass;
  }