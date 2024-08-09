  @Test
  public void setDelete() throws Exception {

    BeanCascadeInfo info = new BeanCascadeInfo();
    info.setDelete(true);
    assertFalse(info.isSave());
    assertTrue(info.isDelete());
    assertFalse(info.isRefresh());
  }