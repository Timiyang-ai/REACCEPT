@Test
  public void testFetch() throws Exception {

    // generate seedlist
    ArrayList<String> urls = new ArrayList<String>();

    addUrl(urls, "index.html");
    addUrl(urls, "pagea.html");
    addUrl(urls, "pageb.html");
    addUrl(urls, "dup_of_pagea.html");
    addUrl(urls, "nested_spider_trap.html");
    addUrl(urls, "exception.html");

    CrawlTestUtil.generateSeedList(fs, urlPath, urls);

    // inject
    InjectorJob injector = new InjectorJob(conf);
    injector.inject(urlPath);

    // generate
    long time = System.currentTimeMillis();
    GeneratorJob g = new GeneratorJob(conf);
    String batchId = g.generate(Long.MAX_VALUE, time, false, false);

    // fetch
    time = System.currentTimeMillis();
    conf.setBoolean(FetcherJob.PARSE_KEY, true);
    FetcherJob fetcher = new FetcherJob(conf);
    fetcher.fetch(batchId, 1, false, -1);

    time = System.currentTimeMillis() - time;

    // verify politeness, time taken should be more than (num_of_pages +1)*delay
    int minimumTime = (int) ((urls.size() + 1) * 1000 * conf.getFloat(
        "fetcher.server.delay", 5));
    assertTrue(time > minimumTime);

    List<URLWebPage> pages = CrawlTestUtil.readContents(webPageStore,
        Mark.FETCH_MARK, (String[]) null);
    assertEquals(urls.size(), pages.size());
    List<String> handledurls = new ArrayList<String>();
    for (URLWebPage up : pages) {
      ByteBuffer bb = up.getDatum().getContent();
      if (bb == null) {
        continue;
      }
      String content = Bytes.toString(bb);
      if (content.indexOf("Nutch fetcher test page") != -1) {
        handledurls.add(up.getUrl());
      }
    }
    Collections.sort(urls);
    Collections.sort(handledurls);

    // verify that enough pages were handled
    assertEquals(urls.size(), handledurls.size());

    // verify that correct pages were handled
    assertTrue(handledurls.containsAll(urls));
    assertTrue(urls.containsAll(handledurls));
  }