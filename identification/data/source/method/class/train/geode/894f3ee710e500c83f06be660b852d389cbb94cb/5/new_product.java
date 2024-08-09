public static Properties getOverriddenDefaults() {
    ProcessLauncherContext context = get();
    if (context == null) {
      return new Properties();
    }
    return context.overriddenDefaults();
  }