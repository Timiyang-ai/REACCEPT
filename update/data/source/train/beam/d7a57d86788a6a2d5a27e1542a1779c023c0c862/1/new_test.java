@Test
  public void testCreateWriter() throws Exception {
    PipelineOptions options = PipelineOptionsFactory.create();
    XmlWriteOperation<Bird> writeOp =
        XmlFileBasedSink.writeOf(testClass, testRootElement, testFilePrefix)
            .createWriteOperation(options);
    XmlWriter<Bird> writer = writeOp.createWriter(options);
    assertEquals(testFilePrefix, writer.getWriteOperation().baseTemporaryFilename);
    assertEquals(testRootElement, writer.getWriteOperation().getSink().rootElementName);
    assertNotNull(writer.marshaller);
  }