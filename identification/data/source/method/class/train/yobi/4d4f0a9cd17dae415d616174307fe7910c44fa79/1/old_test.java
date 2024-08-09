    @Test
    public void handleLinks() {
        // Given
        Document doc = Jsoup.parse(
            "<a href=\"http://y/foo/bar\">external link</a>" +
            "<a href=\"http://y/foo/bar\" rel=\"nofollow\">external link</a>" +
            "<a href=\"http://yobi.io/foo/bar\">internal link</a>" +
            "<a href=\"/foo/bar\">relative link</a>" +
            "<a href=\"http://yobi.io/%ag\">malformed link</a>");

        // When
        NotificationMail.handleLinks(doc);

        // Then
        Elements links = doc.select("a");

        assertThat(links.get(0).attr("rel"))
            .describedAs("rel attribute of external link")
            .isEqualTo(" noreferrer");

        assertThat(links.get(1).attr("rel"))
            .describedAs("rel attribute of external link whose rel attribute was 'nofollow'")
            .isEqualTo("nofollow noreferrer");

        assertThat(links.get(2).hasAttr("rel"))
            .describedAs("rel attribute of internal link contains 'noreferrer'.")
            .isFalse();

        assertThat(links.get(3).hasAttr("rel"))
            .describedAs("rel attribute of relative link contains 'noreferrer'.")
            .isFalse();

        assertThat(links.get(4).attr("rel"))
            .describedAs("rel attribute of malformed link")
            .isEqualTo(" noreferrer");

    }