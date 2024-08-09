@Test
    public void testSearch() {
        System.out.println("search 'romantic'");
        Iterator<Relevance> hits = corpus.search(new BM25(), "romantic");
        while (hits.hasNext()) {
            Relevance hit = hits.next();
            System.out.println(hit.text + "\t" + hit.score);
        }
    }