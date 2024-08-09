public void promoteTrunkToProduction(final String testName, String trunkRevision, String prodRevision,
                                         String username, String password, String author, Map<String, String> metadata) throws StoreException, TestPromotionException {
        promote(testName, Environment.WORKING, trunkRevision, Environment.PRODUCTION, prodRevision, username, password, author, metadata);
    }