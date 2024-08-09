@Test
  public void testCreateWriter() throws Exception {
    PipelineOptions options = PipelineOptionsFactory.create();
    XmlWriteOperation<Bird> writeOp =
        XmlIO.<Bird>write()
            .withRecordClass(Bird.class)
            .withRootElement(testRootElement)
            .to(testFilePrefix)
            .createSink()
            .createWriteOperation();
    XmlWriter<Bird> writer = writeOp.createWriter();
    Path outputPath = new File(testFilePrefix).toPath();
    Path tempPath = new File(writer.getWriteOperation().getTemporaryDirectory().toString())
        .toPath();
    assertThat(tempPath.getParent(), equalTo(outputPath.getParent()));
    assertThat(tempPath.getFileName().toString(), containsString("temp-beam-"));
    assertNotNull(writer.marshaller);
  }