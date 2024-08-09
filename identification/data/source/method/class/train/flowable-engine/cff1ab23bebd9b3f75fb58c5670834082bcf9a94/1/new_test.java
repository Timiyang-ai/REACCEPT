    @Test
    public void notAllOf() {
        assertTrue(CollectionUtil.notAllOf(Arrays.asList("group1", "group2"), Arrays.asList("group3", "group4")));
        assertFalse(CollectionUtil.notAllOf(Arrays.asList("group1", "group2"), Arrays.asList("group1", "group2")));
        assertTrue(CollectionUtil.notAllOf(Arrays.asList("group1", "group2"), Arrays.asList("group2", "group3")));

        assertTrue(CollectionUtil.notAllOf(Arrays.asList("group1", "group2"), "group3"));
        assertFalse(CollectionUtil.notAllOf(Arrays.asList("group1", "group2"),"group2"));

        assertTrue(CollectionUtil.notAllOf("group1, group2", "group3, group4"));
        assertFalse(CollectionUtil.notAllOf("group1, group2", "group1, group2"));
        assertTrue(CollectionUtil.notAllOf("group1, group2", "group2, group3"));

        ObjectMapper mapper = new ObjectMapper();
        assertTrue(CollectionUtil.notAllOf(mapper.valueToTree(Arrays.asList("group1", "group2")), mapper.valueToTree(Arrays.asList("group3", "group4"))));
        assertFalse(CollectionUtil.notAllOf(mapper.valueToTree(Arrays.asList("group1", "group2")), mapper.valueToTree(Arrays.asList("group1", "group2"))));
        assertTrue(CollectionUtil.notAllOf(mapper.valueToTree(Arrays.asList("group1", "group2")), mapper.valueToTree(Arrays.asList("group2", "group3"))));
    }