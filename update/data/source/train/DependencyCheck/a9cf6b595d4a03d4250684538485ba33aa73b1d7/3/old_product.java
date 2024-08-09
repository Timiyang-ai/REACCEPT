public void open() throws IOException {
        directory = Index.getDirectory();
        indexReader = IndexReader.open(directory, true);
        indexSearcher = new IndexSearcher(indexReader);
        analyzer = Index.createAnalyzer(); //use the same analyzer as used when indexing
        //TITLE is the default field because it contains venddor, product, and version all in one.
        queryParser = new QueryParser(Version.LUCENE_35, Fields.TITLE, analyzer);
        indexOpen = true;
    }