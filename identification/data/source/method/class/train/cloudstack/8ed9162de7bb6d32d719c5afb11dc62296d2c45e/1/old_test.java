    @Test
    public void encodeURIComponent() {
        Assert.assertEquals("http://localhost",
                UriUtils.encodeURIComponent("http://localhost"));
        Assert.assertEquals("http://localhost/",
                UriUtils.encodeURIComponent("http://localhost/"));
        Assert.assertEquals("http://localhost/foo/bar",
                UriUtils.encodeURIComponent("http://localhost/foo/bar"));
    }