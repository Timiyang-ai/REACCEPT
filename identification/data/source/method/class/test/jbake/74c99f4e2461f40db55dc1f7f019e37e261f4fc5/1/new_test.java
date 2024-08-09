    @Test
    public void crawl() {
        Crawler crawler = new Crawler(db, config);
        crawler.crawl();

        Assert.assertEquals(4, db.getDocumentCount("post"));
        Assert.assertEquals(3, db.getDocumentCount("page"));

        DocumentList results = db.getPublishedPosts();

        assertThat(results.size()).isEqualTo(3);

        for (Map<String, Object> content : results) {
            assertThat(content)
                    .containsKey(Crawler.Attributes.ROOTPATH)
                    .containsValue("../../../");
        }

        DocumentList allPosts = db.getAllContent("post");

        assertThat(allPosts.size()).isEqualTo(4);

        for (Map<String, Object> content : allPosts) {
            if (content.get(Crawler.Attributes.TITLE).equals("Draft Post")) {
                assertThat(content).containsKey(Crawler.Attributes.DATE);
            }
        }

        // covers bug #213
        DocumentList publishedPostsByTag = db.getPublishedPostsByTag("blog");
        Assert.assertEquals(3, publishedPostsByTag.size());
    }