  @Test
  public void getFile() throws Exception {
    Jdk14FileHandlerAccessor handlerAccessor = new Jdk14FileHandlerAccessor();
    handlerAccessor.setLoggerAccessor(new Jdk14LoggerAccessor());

    Path testPath = Files.createTempDirectory("psi-probe");
    testPath.toFile().deleteOnExit();
    FileHandler target = new FileHandler(testPath.toString() + "test-%g.log", 1024, 3);

    handlerAccessor.setTarget(target);
    handlerAccessor.setIndex(Integer.toString(0));
    Application testApplication = new Application();
    handlerAccessor.setApplication(testApplication);

    File file = handlerAccessor.getFile();

    Assertions.assertThat(file.getAbsolutePath()).isEqualTo(testPath + "test-0.log");

  }