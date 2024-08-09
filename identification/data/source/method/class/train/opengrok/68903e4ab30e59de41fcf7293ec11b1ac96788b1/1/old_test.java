@Test
    public void hasDefinitionAt() {
        Definitions instance = new Definitions();
        instance.addTag(1, "found", "", "");
        assertEquals(instance.hasDefinitionAt("found", 0), false);
        assertEquals(instance.hasDefinitionAt("found", 1), true);
        assertEquals(instance.hasDefinitionAt("found", 2), false);
        assertEquals(instance.hasDefinitionAt("notFound", 0), false);
        assertEquals(instance.hasDefinitionAt("notFound", 1), false);
    }