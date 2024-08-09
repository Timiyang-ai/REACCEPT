@Test
  public void testFetch() throws Exception {

    String batchId = "1234";
    conf.set(GeneratorJob.BATCH_ID, batchId);

    // generate seedlist
    ArrayList<String> normalUrls = new ArrayList<String>();
    ArrayList<String> sitemapUrls = new ArrayList<String>();
    ArrayList<String> urls = new ArrayList<String>();

    addUrl(normalUrls, "index.html");
    addUrl(normalUrls, "pagea.html");
    addUrl(normalUrls, "pageb.html");
    addUrl(normalUrls, "dup_of_pagea.html");
    addUrl(normalUrls, "nested_spider_trap.html");
    addUrl(normalUrls, "exception.html");
    addUrl(sitemapUrls, "sitemap1.xml\t-sitemap");
    addUrl(sitemapUrls, "sitemap2.xml\t-sitemap");
    addUrl(sitemapUrls, "sitemapIndex.xml\t-sitemap");

    urls.addAll(normalUrls);
    urls.addAll(sitemapUrls);

    CrawlTestUtil.generateSeedList(fs, urlPath, urls);

    // inject
    InjectorJob injector = new InjectorJob(conf);
    injector.inject(urlPath);

    // generate
    long time = System.currentTimeMillis();
    GeneratorJob g = new GeneratorJob(conf);
    //  generate for non sitemap
    g.generate(Long.MAX_VALUE, time, false, false, false);
    //    generate for only sitemap
    g.generate(Long.MAX_VALUE, time, false, false, true);

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
    assertEquals(normalUrls.size(), pages.size());
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
    Collections.sort(normalUrls);
    Collections.sort(handledurls);

    // verify that enough pages were handled
    assertEquals(normalUrls.size(), handledurls.size());

    // verify that correct pages were handled
    assertTrue(handledurls.containsAll(normalUrls));
    assertTrue(normalUrls.containsAll(handledurls));
  }