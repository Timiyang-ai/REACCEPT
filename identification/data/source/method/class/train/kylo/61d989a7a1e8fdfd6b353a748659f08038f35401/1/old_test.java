@Test
    public void parseUrl() throws MalformedURLException {
        final Configuration conf = new Configuration(false);
        conf.set("fs.defaultFS", "mock:///");

        Assert.assertEquals(new URL("file:/path/file.ext"), FileSystemUtil.parseUrl("/path/file.ext", null));
        Assert.assertEquals(new URL("hadoop:mock:/path/file.ext"), FileSystemUtil.parseUrl("/path/file.ext", conf));
        Assert.assertEquals(new URL("http://localhost/path/file.ext"), FileSystemUtil.parseUrl("http://localhost/path/file.ext", null));
        Assert.assertEquals(new URL("hadoop:hdfs://localhost:8020/path/file.ext"), FileSystemUtil.parseUrl("hdfs://localhost:8020/path/file.ext", null));
    }