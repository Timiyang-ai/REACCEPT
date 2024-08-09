public static boolean isFlutterModule(@Nullable final Module module) {

    if (module == null) return false;

    // If not IntelliJ, assume a small IDE (no multi-module project support).
    // Look for a module with a flutter-like file structure.
    if (!PlatformUtils.isIntelliJ()) {
      return usesFlutter(module);
    }
    else {
      // [Flutter support enabled for a module] ===
      // [Dart support enabled && referenced Dart SDK is the one inside a Flutter SDK]
      final DartSdk dartSdk = DartPlugin.getDartSdk(module.getProject());
      final String dartSdkPath = dartSdk != null ? dartSdk.getHomePath() : null;
      return dartSdkPath != null && dartSdkPath.endsWith(DART_SDK_SUFFIX) && DartPlugin.isDartSdkEnabled(module);
    }
  }