    @Test
    public void getLastPathElement_empty() {
        assert PathUtils.getLastPathElement(null).equals("");
        assert PathUtils.getLastPathElement("").equals("");
    }