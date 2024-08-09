@VisibleForTesting
  static LabelDescriptor createLabelDescriptor(LabelKey labelKey) {
    LabelDescriptor.Builder builder = LabelDescriptor.newBuilder();
    builder.setKey(labelKey.getKey());
    builder.setDescription(labelKey.getDescription());
    // Now we only support String tags
    builder.setValueType(ValueType.STRING);
    return builder.build();
  }