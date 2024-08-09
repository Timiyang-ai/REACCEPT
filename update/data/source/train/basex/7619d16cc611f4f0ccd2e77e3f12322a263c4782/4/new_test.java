@Test
  public void serialize() {
    serial("<json type='object'/>", "", "{\n}");
    serial("<json objects='json'/>", "", "{\n}");
    serial("<json type='array'/>", "", "[\n]");
    serial("<json arrays='json'/>", "", "[\n]");
    serial("<json type='number'>1</json>", "", "1");
    serial("<json type='array'><_ type='null'/></json>", "", "[\nnull\n]");
    serial("<json type='array'><_ type='string'/></json>", "", "[\n\"\"\n]");
    serial("<json type='array'><_ type='string'>x</_></json>", "", "[\n\"x\"\n]");
    serial("<json type='array'><_ type='number'>1</_></json>", "", "[\n1\n]");
    serial("<json numbers=\"_\" type='array'><_>1</_></json>", "", "[\n1\n]");

    serialError("<json type='o'/>", ""); // invalid type
    serialError("<json type='array'><_ type='number'/></json>", ""); // value needed
    serialError("<json type='array'><_ type='boolean'/></json>", ""); // value needed
    serialError("<json type='array'><_ type='null'>x</_></json>", ""); // no value
  }