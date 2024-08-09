public Object getOptionValue(String optionName) {
    OptionDetails optionData = transitiveOptionsMap.get(optionName);
    return (optionData == null) ? null : optionData.value;
  }