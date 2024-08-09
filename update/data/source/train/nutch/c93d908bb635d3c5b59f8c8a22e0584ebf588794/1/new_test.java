@Test
  public void testFilter() throws IOException, Exception {

    ArrayList<URLCrawlDatum> list = new ArrayList<URLCrawlDatum>();

    list.add(createURLCrawlDatum("http://www.example.com/index.html", 1, 1));
    list.add(createURLCrawlDatum("http://www.example.net/index.html", 1, 1));
    list.add(createURLCrawlDatum("http://www.example.org/index.html", 1, 1));

    createCrawlDB(list);

    Configuration myConfiguration = new Configuration(conf);
    myConfiguration.set("urlfilter.suffix.file", "filter-all.txt");

    Path generatedSegment = generateFetchlist(Integer.MAX_VALUE,
        myConfiguration, true);

    Assert.assertNull("should be null (0 entries)", generatedSegment);

    generatedSegment = generateFetchlist(Integer.MAX_VALUE, myConfiguration,
        false);

    Path fetchlistPath = new Path(new Path(generatedSegment,
        CrawlDatum.GENERATE_DIR_NAME), "part-r-00000");

    ArrayList<URLCrawlDatum> fetchList = readContents(fetchlistPath);

    // verify nothing got filtered
    Assert.assertEquals(list.size(), fetchList.size());

  }