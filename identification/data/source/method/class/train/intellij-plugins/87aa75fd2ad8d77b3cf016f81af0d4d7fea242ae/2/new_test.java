    @Test
    public void getFullComponentPackage_empty() {
        assert PathUtils.getFullComponentPackage(null, null).equals("");
        assert PathUtils.getFullComponentPackage("", "").equals("");
        assert PathUtils.getFullComponentPackage(null, "").equals("");
        assert PathUtils.getFullComponentPackage("", null).equals("");
    }