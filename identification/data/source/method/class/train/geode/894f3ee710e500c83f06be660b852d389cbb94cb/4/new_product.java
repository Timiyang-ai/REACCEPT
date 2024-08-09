public static boolean isRedirectingOutput() {
    ProcessLauncherContext context = get();
    if (context == null) {
      return REDIRECT_OUTPUT_DEFAULT;
    }
    return context.redirectOutput();
  }