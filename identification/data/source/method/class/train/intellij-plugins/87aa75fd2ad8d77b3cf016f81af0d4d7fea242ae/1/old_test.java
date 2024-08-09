    @Test
    public void packageIntoPath_empty() {
        assert PathUtils.packageIntoPath(null, true).equals("");
        assert PathUtils.packageIntoPath(null, false).equals("");

        assert PathUtils.packageIntoPath("", true).equals("");
        assert PathUtils.packageIntoPath("", false).equals("");
    }