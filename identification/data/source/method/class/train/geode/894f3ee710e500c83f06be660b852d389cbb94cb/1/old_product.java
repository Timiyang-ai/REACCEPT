public static Properties getOverriddenDefaults() {
    final ProcessLauncherContext context = get();
    if (context == null) {
      return OVERRIDDEN_DEFAULTS_DEFAULT;
    }
    return context.overriddenDefaults();
  }