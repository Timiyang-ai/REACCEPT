@Test
  public void testMain() throws Exception {
    String[] args = new String[] {
      "--input", inputDir.getAbsolutePath(),  
      "--output", outputDir.getAbsolutePath(),
      "--charset", "UTF-8",
      "--keyPrefix", "TEST"
    };
    
    // run the application's main method
    SequenceFilesFromMailArchives.main(args);
    
    // app should create a single SequenceFile named "chunk-0"
    // in the output dir
    File expectedChunkFile = new File(outputDir, "chunk-0");
    String expectedChunkPath = expectedChunkFile.getAbsolutePath();
    assertTrue("Expected chunk file "+expectedChunkPath+" not found!", 
        expectedChunkFile.isFile());

    Text key = new Text();
    Text value = new Text();
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(conf);
    SequenceFile.Reader seqFileReader = null;
    try {
      seqFileReader = new SequenceFile.Reader(fs, new Path(expectedChunkPath), conf);
      assertTrue("First key/value pair not found!", seqFileReader.next(key, value));
      
      assertEquals("TEST/subdir/mail-messages.gz/"+testVars[0][0], key.toString());
      assertEquals(testVars[0][1]+testVars[0][2], value.toString());
  
      assertTrue("Second key/value pair not found!", seqFileReader.next(key, value));
      assertEquals("TEST/subdir/mail-messages.gz/"+testVars[1][0], key.toString());
      assertEquals(testVars[1][1]+testVars[1][2], value.toString());
  
      assertFalse("Only two key/value pairs expected!", seqFileReader.next(key, value));
    } finally {
      if (seqFileReader != null) {
        try {
          seqFileReader.close();
        } catch (Exception ignore) {}
      }
    }
  }