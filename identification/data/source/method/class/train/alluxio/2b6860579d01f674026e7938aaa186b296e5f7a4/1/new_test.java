  @Test
  public void parseSpaceSize() {
    long max = 10240;
    for (long k = 0; k < max; k++) {
      assertEquals(k / 10, FormatUtils.parseSpaceSize(k / 10.0 + "b"));
      assertEquals(k / 10, FormatUtils.parseSpaceSize(k / 10.0 + "B"));
      assertEquals(k / 10, FormatUtils.parseSpaceSize(k / 10.0 + ""));
    }
    for (long k = 0; k < max; k++) {
      assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "kb"));
      assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Kb"));
      assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "KB"));
      assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "kB"));
      assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "k"));
      assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "K"));
    }
    for (long k = 0; k < max; k++) {
      assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "mb"));
      assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Mb"));
      assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "MB"));
      assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "mB"));
      assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "m"));
      assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "M"));
    }
    for (long k = 0; k < max; k++) {
      assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "gb"));
      assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Gb"));
      assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "GB"));
      assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "gB"));
      assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "g"));
      assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "G"));
    }
    for (long k = 0; k < max; k++) {
      assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "tb"));
      assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Tb"));
      assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "TB"));
      assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "tB"));
      assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "t"));
      assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "T"));
    }
    // We stop the pb test before 8192, since 8192 petabytes is beyond the scope of a java long.
    for (long k = 0; k < 8192; k++) {
      assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "pb"));
      assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Pb"));
      assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "PB"));
      assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "pB"));
      assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "p"));
      assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "P"));
    }
  }