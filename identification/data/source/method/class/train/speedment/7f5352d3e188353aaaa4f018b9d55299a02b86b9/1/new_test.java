    @Test
    void stream() {
        assertNotEquals(0, StandardJavaTypeMapping.stream().count());
    }