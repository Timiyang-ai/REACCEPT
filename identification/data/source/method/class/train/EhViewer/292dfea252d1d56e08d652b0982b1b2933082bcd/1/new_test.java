@Test
  public void testGetCategory1() {
    assertEquals(EhUtils.CATEGORY_NON_H, EhUtils.getCategory("Non-H"));
    assertEquals(EhUtils.CATEGORY_ARTIST_CG, EhUtils.getCategory("Artist CG Sets"));
    assertEquals(EhUtils.CATEGORY_DOUJINSHI, EhUtils.getCategory("doujinshi"));
    assertEquals(EhUtils.CATEGORY_UNKNOWN, EhUtils.getCategory("HA HI"));
    assertEquals(EhUtils.CATEGORY_UNKNOWN, EhUtils.getCategory(null));
  }