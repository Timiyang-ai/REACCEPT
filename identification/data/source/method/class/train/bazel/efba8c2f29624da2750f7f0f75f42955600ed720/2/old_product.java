public static DottedVersion getSdkVersionForPlatform(
      RuleContext ruleContext, ApplePlatform platform) {
    XcodeConfigProvider versions = ruleContext.getPrerequisite(
        XcodeConfigRule.XCODE_CONFIG_ATTR_NAME,
        RuleConfiguredTarget.Mode.TARGET,
        XcodeConfigProvider.class);
    DottedVersion fromProvider = versions.getSdkVersionForPlatform(platform);
    DottedVersion fromConfig = ruleContext.getFragment(AppleConfiguration.class)
        .getSdkVersionForPlatform(platform);
    // This sanity check is there to keep this provider in sync with AppleConfiguration until the
    // latter can be removed. Tracking bug: https://github.com/bazelbuild/bazel/issues/3424
    Preconditions.checkState(fromProvider.equals(fromConfig));
    return fromProvider;
  }