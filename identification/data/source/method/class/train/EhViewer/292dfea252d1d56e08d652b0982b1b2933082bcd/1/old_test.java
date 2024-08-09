@Test
  public void testGetCategory1() {
    assertEquals(EhUtils.NON_H, EhUtils.getCategory("Non-H"));
    assertEquals(EhUtils.ARTIST_CG, EhUtils.getCategory("Artist CG Sets"));
    assertEquals(EhUtils.DOUJINSHI, EhUtils.getCategory("doujinshi"));
    assertEquals(EhUtils.UNKNOWN, EhUtils.getCategory("HA HI"));
    assertEquals(EhUtils.UNKNOWN, EhUtils.getCategory(null));
  }