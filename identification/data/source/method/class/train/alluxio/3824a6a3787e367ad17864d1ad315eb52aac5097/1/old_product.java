public static CompleteFilePOptions completeFileDefaults() {
    return CompleteFilePOptions.newBuilder()
        .setCommonOptions(commonDefaults())
        .setUfsLength(0)
        .build();
  }