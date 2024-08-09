@Test
  public void testMain() throws Exception {
    String[] args = {
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
    Assert.assertTrue("Expected chunk file "+expectedChunkPath+" not found!", expectedChunkFile.isFile());


    Configuration conf = new Configuration();
    SequenceFileIterator<Text,Text> iterator =
        new SequenceFileIterator<Text,Text>(new Path(expectedChunkPath), true, conf);

    Assert.assertTrue("First key/value pair not found!", iterator.hasNext());
    Pair<Text,Text> record = iterator.next();

    Assert.assertEquals("TEST/subdir/mail-messages.gz/" + testVars[0][0], record.getFirst().toString());
    Assert.assertEquals(testVars[0][1]+testVars[0][2], record.getSecond().toString());

    Assert.assertTrue("Second key/value pair not found!", iterator.hasNext());
    record = iterator.next();
    Assert.assertEquals("TEST/subdir/mail-messages.gz/"+testVars[1][0], record.getFirst().toString());
    Assert.assertEquals(testVars[1][1]+testVars[1][2], record.getSecond().toString());

    Assert.assertFalse("Only two key/value pairs expected!", iterator.hasNext());
  }