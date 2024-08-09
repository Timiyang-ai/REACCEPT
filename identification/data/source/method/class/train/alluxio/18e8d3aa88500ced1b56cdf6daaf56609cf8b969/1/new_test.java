@Test
  public void toThrift() {
    Random random = new Random();
    boolean persisted = random.nextBoolean();
    boolean pinned = random.nextBoolean();
    long ttl = random.nextLong();

    SetAttributeOptions options = SetAttributeOptions.defaults();
    options.setPersisted(persisted);
    options.setPinned(pinned);
    options.setTtl(ttl);
    options.setTtlAction(TtlAction.FREE);
    SetAttributeTOptions thriftOptions = options.toThrift();

    assertTrue(thriftOptions.isSetPersisted());
    assertEquals(persisted, thriftOptions.isPersisted());
    assertTrue(thriftOptions.isSetPinned());
    assertEquals(pinned, thriftOptions.isPinned());
    assertTrue(thriftOptions.isSetTtl());
    assertEquals(TTtlAction.Free, thriftOptions.getTtlAction());
    assertEquals(ttl, thriftOptions.getTtl());
  }