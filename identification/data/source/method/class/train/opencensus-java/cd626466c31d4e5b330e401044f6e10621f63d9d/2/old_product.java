@VisibleForTesting
  static LabelDescriptor createLabelDescriptor(TagKey tagKey) {
    LabelDescriptor.Builder builder = LabelDescriptor.newBuilder();
    builder.setKey(tagKey.getName());
    builder.setDescription(LABEL_DESCRIPTION);
    // Now we only support String tags
    builder.setValueType(ValueType.STRING);
    return builder.build();
  }