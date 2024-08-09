@Test
  public void toThrift() throws IOException {
    Random random = new Random();
    String owner = CommonUtils.randomAlphaNumString(10);
    String group = CommonUtils.randomAlphaNumString(10);
    Mode mode = new Mode((short) random.nextInt());

    CreateUfsFileOptions options = CreateUfsFileOptions.defaults();
    options.setOwner(owner);
    options.setGroup(group);
    options.setMode(mode);

    CreateUfsFileTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(owner, thriftOptions.getOwner());
    Assert.assertEquals(group, thriftOptions.getGroup());
    Assert.assertEquals(mode.toShort(), thriftOptions.getMode());
  }