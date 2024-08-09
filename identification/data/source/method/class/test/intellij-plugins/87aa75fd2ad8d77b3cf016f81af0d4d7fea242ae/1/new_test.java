    @Test
    public void pathIntoPackage_empty() {
        assert PathUtils.pathIntoPackage(null, true).equals("");
        assert PathUtils.pathIntoPackage(null, false).equals("");

        assert PathUtils.pathIntoPackage("", true).equals("");
        assert PathUtils.pathIntoPackage("", false).equals("");
    }