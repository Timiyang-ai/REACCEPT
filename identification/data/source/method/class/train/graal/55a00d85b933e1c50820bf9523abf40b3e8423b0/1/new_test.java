    @Test
    public void loadComponentFiles() throws Exception {
        copyDir("list1", registryPath);
        ComponentInfo info = loadLastComponent("fastr");
        storage.loadComponentFiles(info);
        List<String> files = info.getPaths();
        assertEquals(Arrays.asList(
                        "bin/", "bin/R", "bin/Rscript"), files.subList(0, 3));
    }