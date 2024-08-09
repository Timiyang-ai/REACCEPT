@Test
    public void hasDefinitionAt() {
        Definitions instance = new Definitions();
        String[] type= new String[1];
        type[0]="";
        instance.addTag(1, "found", "", "");
        assertEquals(instance.hasDefinitionAt("found", 0, type), false);
        assertEquals(instance.hasDefinitionAt("found", 1, type), true);
        assertEquals(instance.hasDefinitionAt("found", 2, type), false);
        assertEquals(instance.hasDefinitionAt("notFound", 0, type), false);
        assertEquals(instance.hasDefinitionAt("notFound", 1, type), false);
    }