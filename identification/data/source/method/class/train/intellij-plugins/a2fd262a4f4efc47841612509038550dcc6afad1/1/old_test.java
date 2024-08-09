    @Test
    public void getComponentFileName_empty() {
        assert PathUtils.getComponentFileName(null).equals("");
        assert PathUtils.getComponentFileName("").equals("");
    }