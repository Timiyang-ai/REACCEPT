@Test
  public void testCreateWriter() throws Exception {
    PipelineOptions options = PipelineOptionsFactory.create();
    XmlWriteOperation<Bird> writeOp =
        XmlSink.writeOf(testClass, testRootElement, testFilePrefix)
            .createWriteOperation(options);
    XmlWriter<Bird> writer = writeOp.createWriter(options);
    Path outputPath = new File(testFilePrefix).toPath();
    Path tempPath = new File(writer.getWriteOperation().tempDirectory.get()).toPath();
    assertEquals(outputPath.getParent(), tempPath.getParent());
    assertThat(
        tempPath.getFileName().toString(), containsString("temp-beam-" + outputPath.getFileName()));
    assertEquals(testRootElement, writer.getWriteOperation().getSink().rootElementName);
    assertNotNull(writer.marshaller);
  }