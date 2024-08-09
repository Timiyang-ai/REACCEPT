@Test
    public void testDeleteComponent() throws Exception {
        copyDir("list2", registryPath);
        storage.deleteComponent("fastr");

        Path fastrComp = registryPath.resolve("fastr.component");
        Path fastrList = registryPath.resolve("fastr.filelist");

        assertFalse(Files.exists(fastrComp));
        assertFalse(Files.exists(fastrList));

        storage.deleteComponent("sulong");
        Path sulongComp = registryPath.resolve("sulong.component");
        assertFalse(Files.exists(sulongComp));

        storage.deleteComponent("leftover");
        Path leftoverList = registryPath.resolve("leftover.filelist");
        assertFalse(Files.exists(leftoverList));
    }