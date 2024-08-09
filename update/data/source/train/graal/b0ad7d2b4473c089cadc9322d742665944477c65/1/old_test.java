@Test
    public void testLoadComponentMetadata() throws Exception {
        copyDir("list1", registryPath);
        ComponentInfo info = storage.loadComponentMetadata("fastr");
        assertEquals("org.graalvm.fastr", info.getId());
        assertEquals("1.0", info.getVersionString());
        assertEquals("0.32", info.getRequiredGraalValues().get("graalvm_version"));
    }