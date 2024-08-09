@Test
  public void bind() throws IOException {
    equals("1", "-ba=1 -q$a");
    equals("2", "-ba=1 -bb=1 -q$a+$b");
    equals("3", "-ba=1,b=2 -q$a+$b");
    IN.write(token("$a"));
    equals("4", "-ba=4 " + IN);
  }