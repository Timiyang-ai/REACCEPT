@Test
  public void serialize() {
    serialError("<a/>", ""); // invalid tag
    serialError("<json type='o'/>", ""); // invalid type
    serial("<json type='object'/>", "", "{}");
    serial("<json objects='json'/>", "", "{}");
    serial("<json type='array'/>", "", "[]");
    serial("<json arrays='json'/>", "", "[]");
    serial("<json type='number'>1</json>", "", "1"); // no text allowed in json tag
    serial("<json type='array'><_ type='null'/></json>", "", "[null]");
    serialError("<json type='array'><_ type='number'/></json>", ""); // value needed
    serialError("<json type='array'><_ type='boolean'/></json>", ""); // value needed
    serialError("<json type='array'><_ type='null'>x</_></json>", ""); // no value
    serial("<json type='array'><_ type='string'/></json>", "", "[\"\"]");
    serial("<json type='array'><_ type='string'>x</_></json>", "", "[\"x\"]");
    serial("<json type='array'><_ type='number'>1</_></json>", "", "[1]");
    serial("<json numbers=\"_\" type='array'><_>1</_></json>", "", "[1]");
  }