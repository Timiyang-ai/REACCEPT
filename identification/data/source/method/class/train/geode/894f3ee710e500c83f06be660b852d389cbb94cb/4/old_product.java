public static boolean isRedirectingOutput() {
    final ProcessLauncherContext context = get();
    if (context == null) {
      return REDIRECT_OUTPUT_DEFAULT;
    }
    return context.redirectOutput();
  }