static Platform findPlatform() {
    Platform jre8 = Jre8.buildIfSupported();

    if (jre8 != null) return jre8;

    Platform jre7 = Jre7.buildIfSupported();

    if (jre7 != null) return jre7;

    // compatible with JRE 6
    return Jre6.build();
  }