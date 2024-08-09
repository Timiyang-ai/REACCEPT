public void linkToHTML() {
        String md = "test@test.com";
        String html = Markdowns.linkToHtml(md);
        System.out.println(html);
        Assert.assertEquals(html, "<p><a href=\"mailto:&#116;e&#115;&#x74;&#x40;&#x74;&#x65;&#115;&#x74;&#x2e;c&#111;&#x6d;\">&#116;e&#115;&#x74;&#x40;&#x74;&#x65;&#115;&#x74;&#x2e;c&#111;&#x6d;</a></p>");
    }