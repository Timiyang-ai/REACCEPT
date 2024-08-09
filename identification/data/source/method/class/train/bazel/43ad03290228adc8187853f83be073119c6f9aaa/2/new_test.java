  @Test
  public void addPath() throws Exception {
    PathFragment pf = PathFragment.create("/etc/pwd");
    assertThat(new Fingerprint().addPath(pf).hexDigestAndReset())
        .isEqualTo("0b229115c2da46773ff38528420b922488dd564ddb3c0c861fb1c77ae8525f9b");
    Path p = new InMemoryFileSystem(BlazeClock.instance()).getPath(pf);
    assertThat(new Fingerprint().addPath(p).hexDigestAndReset())
        .isEqualTo("0b229115c2da46773ff38528420b922488dd564ddb3c0c861fb1c77ae8525f9b");
  }