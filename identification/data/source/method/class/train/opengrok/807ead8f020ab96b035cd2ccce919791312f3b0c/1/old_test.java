    @Test
    public void uid2url() {
        assertEquals("/etc/passwd", Util.uid2url(
                Util.path2uid("/etc/passwd", "date")));
    }