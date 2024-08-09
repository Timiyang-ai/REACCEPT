    @Test
    public void getFirstPathElement_empty() {
        assert PathUtils.getFirstPathElement(null).equals("");
        assert PathUtils.getFirstPathElement("").equals("");
    }