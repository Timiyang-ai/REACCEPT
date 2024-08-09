@Test @Config(minSdk = VERSION_CODES.N)
  public void applyRules_rtlScript() throws Exception {
    applyQualifiers("he");
    DeviceConfig.applyRules(configuration, displayMetrics, apiLevel);

    assertThat(asQualifierString())
        .isEqualTo("iw-ldrtl-sw320dp-w320dp-h470dp-normal-notlong-notround-" + optsForO + "port-notnight-mdpi-finger-keyssoft-nokeys-navhidden-nonav");
  }