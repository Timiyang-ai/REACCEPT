  @Test
  public void resolve() throws IOException {
    final Path root = random();
    final SitePaths site = new SitePaths(root);

    PathSubject.assertThat(site.resolve(null)).isNull();
    PathSubject.assertThat(site.resolve("")).isNull();

    PathSubject.assertThat(site.resolve("a")).isNotNull();
    PathSubject.assertThat(site.resolve("a"))
        .isEqualTo(root.resolve("a").toAbsolutePath().normalize());

    final String pfx = HostPlatform.isWin32() ? "C:/" : "/";
    PathSubject.assertThat(site.resolve(pfx + "a")).isNotNull();
    PathSubject.assertThat(site.resolve(pfx + "a")).isEqualTo(Paths.get(pfx + "a"));
  }