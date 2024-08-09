    @Test
    public void union() {
        annotations = builder().set("foo", "1").set("bar", "2").remove("buz").build();
        assertEquals("incorrect keys", of("foo", "bar", "buz"), annotations.keys());

        SparseAnnotations updates = builder().remove("foo").set("bar", "3").set("goo", "4").remove("fuzz").build();

        SparseAnnotations result = DefaultAnnotations.union(annotations, updates);

        assertTrue("remove instruction in original remains", result.isRemoved("buz"));
        assertTrue("remove instruction in update remains", result.isRemoved("fuzz"));
        assertEquals("incorrect keys", of("buz", "goo", "bar", "fuzz"), result.keys());
        assertNull("incorrect value", result.value("foo"));
        assertEquals("incorrect value", "3", result.value("bar"));
        assertEquals("incorrect value", "4", result.value("goo"));
    }