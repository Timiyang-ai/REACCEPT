    @Test
    public void sanitizePaths() {
        String sep = File.pathSeparator;

        // where are we now?
        String here = SmartFile.sanitize(".");

        String cp1before = "/a/b/c" + sep + "qqq" + sep + "qqq" + sep + "qqq" + sep + "qqq" + sep + "qqq" + sep + "./././qqq/./." + sep + "z/e";
        String cp1expected = "/a/b/c" + sep + here + "/qqq" + sep + here + "/z/e";

        if (sep.equals(";")) {
            // Windows -- drive letter is needed...
            String drive = here.substring(0, 2);
            cp1expected = drive + "/a/b/c;" + here + "/qqq;" + here + "/z/e";
        }
        assertEquals(cp1expected, SmartFile.sanitizePaths(cp1before));
    }