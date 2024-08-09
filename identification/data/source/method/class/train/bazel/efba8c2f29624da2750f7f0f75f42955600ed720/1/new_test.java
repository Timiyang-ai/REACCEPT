  private DottedVersion getSdkVersionForPlatform(ApplePlatform platform) throws Exception {
    ConfiguredTarget xcodeConfig = getConfiguredTarget("//xcode:foo");
    XcodeConfigInfo provider = xcodeConfig.get(XcodeConfigInfo.PROVIDER);
    return provider.getSdkVersionForPlatform(platform);
  }