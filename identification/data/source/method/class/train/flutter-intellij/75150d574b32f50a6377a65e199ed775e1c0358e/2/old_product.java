public static boolean isFlutterModule(@Nullable Module module) {
    // If not IntelliJ, assume a small IDE (no multi-module project support).
    // Look for a module with a flutter-like file structure.
    if (!PlatformUtils.isIntelliJ()) {
      return module != null && usesFlutter(module);
    }
    else {
      return module != null && ModuleType.is(module, FlutterModuleType.getInstance());
    }
  }