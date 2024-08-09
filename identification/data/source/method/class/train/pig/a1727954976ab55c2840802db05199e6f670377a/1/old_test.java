@Test
  public void testSplit2() throws IOException, ParseException {
    String STR_SCHEMA = "c:collection(a:double, b:float, c:bytes),c2:collection(r1:record(f1:int, f2:string), d:string),c3:collection(c3_1:collection(e:int,f:bool))";
    String STR_STORAGE = "[d]";
    conf = new Configuration();
    conf.setInt("table.output.tfile.minBlock.size", 64 * 1024);
    conf.setInt("table.input.split.minSize", 64 * 1024);
    conf.set("table.output.tfile.compression", "none");

    RawLocalFileSystem rawLFS = new RawLocalFileSystem();
    fs = new LocalFileSystem(rawLFS);
    path = new Path(fs.getWorkingDirectory(), this.getClass().getSimpleName());
    fs = path.getFileSystem(conf);
    // drop any previous tables
    BasicTable.drop(path, conf);
    try {
      BasicTable.Writer writer = new BasicTable.Writer(path, STR_SCHEMA,
          STR_STORAGE, false, conf);
      Assert.fail("should throw exception");
    } catch (Exception e) {
      System.out.println(e);
    }
  }