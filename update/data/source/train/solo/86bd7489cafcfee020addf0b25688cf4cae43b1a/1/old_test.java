@Test(dependsOnMethods = "getRecentArticles")
    public void getArticleContent() throws Exception {
        final ArticleQueryService articleQueryService = getArticleQueryService();

        final List<JSONObject> articles = articleQueryService.getRecentArticles(10);

        Assert.assertEquals(articles.size(), 1);

        final String articleId = articles.get(0).getString(Keys.OBJECT_ID);

        Assert.assertNotNull(articleQueryService.getArticleContent(articleId));
    }