public int getNextRunNumber(String commitId) {

        SearchResponse response = getRequest(DAO_INDEX_KEY, DAO_TYPE_KEY)
                .setQuery(termQuery("commitId", commitId))
                .setSize(0)
                .execute()
                .actionGet();

        return (int) response.getHits().totalHits() + 1;
    }