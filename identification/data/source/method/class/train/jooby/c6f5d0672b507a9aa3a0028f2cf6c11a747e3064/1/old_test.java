  @Test
  public void pathKeys() {
    pathKeys("/*", keys -> assertEquals(1, keys.size()));

    pathKeys("/foo/?*", keys -> assertEquals(1, keys.size()));

    pathKeys("/foo", keys -> assertEquals(0, keys.size()));
    pathKeys("/", keys -> assertEquals(0, keys.size()));
    pathKeys("/foo/bar", keys -> assertEquals(0, keys.size()));
    pathKeys("/foo/*", keys -> assertEquals(1, keys.size()));
    pathKeys("/foo/{x}", keys -> assertEquals(1, keys.size()));
  }