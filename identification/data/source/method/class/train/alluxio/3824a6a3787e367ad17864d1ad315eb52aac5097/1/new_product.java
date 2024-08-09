public static CompleteFilePOptions completeFileDefaults() {
    return CompleteFilePOptions.newBuilder()
        .setCommonOptions(FileSystemOptions.commonDefaults(ServerConfiguration.global()))
        .setUfsLength(0)
        .build();
  }