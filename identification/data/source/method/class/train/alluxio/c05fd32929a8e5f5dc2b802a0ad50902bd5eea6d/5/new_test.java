@Test
  public void toAclActions() {
    for (Mode.Bits bits : Mode.Bits.values()) {
      Assert.assertEquals(bits, new AclActions(bits.toAclActionSet()).toModeBits());
    }
  }