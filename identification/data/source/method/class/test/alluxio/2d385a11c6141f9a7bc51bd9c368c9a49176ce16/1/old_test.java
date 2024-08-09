  @Test
  public void parse() {
    List<String> definitions = Arrays.asList(
        "write(type)",
        " write(type) ",
        " write( t ) ",
        "write(t).option(some.opt1, 100).option(some.opt2, 2gb)",
        "write(t) .option(opt1 , 100) .option(opt2 , 2gb )",
        "write(t).option(opt1, 10.0)",
        "write(t)\n\t  .option(opt1, 10.0)",
        "write(\n\t  t)"
    );

    parseValidInternal(definitions);
  }