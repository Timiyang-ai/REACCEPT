  private static float computeNorm(Similarity sim, String field, int length) throws IOException {
    String value = IntStream.range(0, length).mapToObj(i -> "a").collect(Collectors.joining(" "));
    Directory dir = new ByteBuffersDirectory();
    IndexWriter w = new IndexWriter(dir, newIndexWriterConfig().setSimilarity(sim));
    w.addDocument(Collections.singleton(newTextField(field, value, Store.NO)));
    DirectoryReader reader = DirectoryReader.open(w);
    w.close();
    IndexSearcher searcher = new IndexSearcher(reader);
    searcher.setSimilarity(sim);
    Explanation expl = searcher.explain(new TermQuery(new Term(field, "a")), 0);
    reader.close();
    dir.close();
    Explanation norm = findExplanation(expl, "fieldNorm");
    assertNotNull(norm);
    return norm.getValue().floatValue();
  }