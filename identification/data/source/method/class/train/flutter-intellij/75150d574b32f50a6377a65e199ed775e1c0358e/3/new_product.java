public static boolean isFlutterModule(@Nullable final Module module) {
    if (module == null || module.isDisposed()) return false;

    if (PlatformUtils.isIntelliJ() || FlutterUtils.isAndroidStudio()) {
      // [Flutter support enabled for a module] ===
      //   [Dart support enabled && referenced Dart SDK is the one inside a Flutter SDK]
      final DartSdk dartSdk = DartPlugin.getDartSdk(module.getProject());
      final String dartSdkPath = dartSdk != null ? dartSdk.getHomePath() : null;
      return dartSdkPath != null && dartSdkPath.endsWith(DART_SDK_SUFFIX) && DartPlugin.isDartSdkEnabled(module);
    }
    else {
      // If not IntelliJ, assume a small IDE (no multi-module project support).
      return declaresFlutter(module);
    }
  }