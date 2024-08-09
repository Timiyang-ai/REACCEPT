@Test
  public void parseSpaceSize() {
    long max = 10240;
    for (long k = 0; k < max; k++) {
      Assert.assertEquals(k / 10, FormatUtils.parseSpaceSize(k / 10.0 + "b"));
      Assert.assertEquals(k / 10, FormatUtils.parseSpaceSize(k / 10.0 + "B"));
      Assert.assertEquals(k / 10, FormatUtils.parseSpaceSize(k / 10.0 + ""));
    }
    for (long k = 0; k < max; k++) {
      Assert.assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "kb"));
      Assert.assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Kb"));
      Assert.assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "KB"));
      Assert.assertEquals(k * Constants.KB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "kB"));
    }
    for (long k = 0; k < max; k++) {
      Assert.assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "mb"));
      Assert.assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Mb"));
      Assert.assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "MB"));
      Assert.assertEquals(k * Constants.MB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "mB"));
    }
    for (long k = 0; k < max; k++) {
      Assert.assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "gb"));
      Assert.assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Gb"));
      Assert.assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "GB"));
      Assert.assertEquals(k * Constants.GB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "gB"));
    }
    for (long k = 0; k < max; k++) {
      Assert.assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "tb"));
      Assert.assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Tb"));
      Assert.assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "TB"));
      Assert.assertEquals(k * Constants.TB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "tB"));
    }
    // We stop the pb test before 8192, since 8192 petabytes is beyond the scope of a java long.
    for (long k = 0; k < 8192; k++) {
      Assert.assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "pb"));
      Assert.assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "Pb"));
      Assert.assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "PB"));
      Assert.assertEquals(k * Constants.PB / 10, FormatUtils.parseSpaceSize(k / 10.0 + "pB"));
    }
  }