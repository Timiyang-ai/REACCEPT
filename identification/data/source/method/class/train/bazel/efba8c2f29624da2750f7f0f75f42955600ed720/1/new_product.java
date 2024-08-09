public static DottedVersion getSdkVersionForPlatform(
      RuleContext ruleContext, ApplePlatform platform) {
    XcodeConfigProvider versions = ruleContext.getPrerequisite(
        XcodeConfigRule.XCODE_CONFIG_ATTR_NAME,
        RuleConfiguredTarget.Mode.TARGET,
        XcodeConfigProvider.PROVIDER);
    return versions.getSdkVersionForPlatform(platform);
  }