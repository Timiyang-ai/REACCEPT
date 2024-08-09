protected List<Entry> searchCPE(String vendor, String product, String version,
            Set<String> vendorWeightings, Set<String> productWeightings)
            throws CorruptIndexException, IOException, ParseException {
        ArrayList<Entry> ret = new ArrayList<Entry>(MAX_QUERY_RESULTS);

        String searchString = buildSearch(vendor, product, version, vendorWeightings, productWeightings);
        if (searchString == null) {
            return ret;
        }
        Query query = queryParser.parse(searchString);
        TopDocs docs = indexSearcher.search(query, MAX_QUERY_RESULTS);
        for (ScoreDoc d : docs.scoreDocs) {
            Document doc = indexSearcher.doc(d.doc);
            Entry entry = Entry.parse(doc);
            entry.setSearchScore(d.score);
            if (!ret.contains(entry)) {
                ret.add(entry);
            }
        }
        return ret;
    }