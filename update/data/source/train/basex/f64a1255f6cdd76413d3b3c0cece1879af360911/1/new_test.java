@Test
  public void bind() throws IOException {
    equals("1", "-ba=1", "-q$a");
    equals("2", "-ba=1", "-bb=1", "-q$a+$b");
    equals("3", "-ba=1", "-bb=2", "-q$a+$b");
    IN.write(token("$a"));
    equals("4", "-ba=4", IN.toString());
    equals("5,6;7'", "-ba=5,6;7'", "-q$a");
    // bind quote (to be checked in client/server mode)
    //equals("\"", "-ba=\"", "-q$a");
    // bind variables with namespaces
    equals("8", "-b{}a=8", "-q$a");
    equals("9", "-b'':a=9", "-q$a");
    equals("A", "-b{URI}a=A", "-qdeclare namespace a='URI'; $a:a");
    equals("B", "-b'URI':b=B", "-qdeclare namespace b='URI'; $b:b");
  }