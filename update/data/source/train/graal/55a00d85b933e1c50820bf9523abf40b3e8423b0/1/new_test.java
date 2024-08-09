@Test
    public void testDeleteComponent() throws Exception {
        copyDir("list2", registryPath);
        storage.deleteComponent("fastr");

        Path fastrComp = registryPath.resolve(SystemUtils.fileName("fastr.component"));
        Path fastrList = registryPath.resolve(SystemUtils.fileName("fastr.filelist"));

        assertFalse(Files.exists(fastrComp));
        assertFalse(Files.exists(fastrList));

        storage.deleteComponent("sulong");
        Path sulongComp = registryPath.resolve(SystemUtils.fileName("sulong.component"));
        assertFalse(Files.exists(sulongComp));

        storage.deleteComponent("leftover");
        Path leftoverList = registryPath.resolve(SystemUtils.fileName("leftover.filelist"));
        assertFalse(Files.exists(leftoverList));
    }