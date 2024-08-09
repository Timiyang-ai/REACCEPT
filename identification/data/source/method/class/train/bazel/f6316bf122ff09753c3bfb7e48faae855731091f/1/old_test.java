  @Test
  public void freeze() throws Exception {
    Mutability mutability = Mutability.create("test");
    DummyFreezable dummy = new DummyFreezable(mutability);

    Mutability.checkMutable(dummy, mutability);
    mutability.freeze();
    assertCheckMutableFailsBecauseFrozen(dummy, mutability);
  }