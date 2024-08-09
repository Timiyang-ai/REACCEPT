public void open() throws IOException {
        cpe = new Index();
        cpe.open();
        indexSearcher = cpe.getIndexSearcher();
        Analyzer analyzer = cpe.getAnalyzer();
        //TITLE is the default field because it contains venddor, product, and version all in one.
        queryParser = new QueryParser(Version.LUCENE_35, Fields.TITLE, analyzer);
    }