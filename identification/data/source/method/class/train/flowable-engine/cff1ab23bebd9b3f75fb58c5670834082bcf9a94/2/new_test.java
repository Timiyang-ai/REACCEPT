    @Test
    public void noneOf() {
        assertTrue(CollectionUtil.noneOf(Arrays.asList("group1", "group2"), Arrays.asList("group3", "group4")));
        assertFalse(CollectionUtil.noneOf(Arrays.asList("group1", "group2"), Arrays.asList("group1", "group2")));
        assertFalse(CollectionUtil.noneOf(Arrays.asList("group1", "group2"), Arrays.asList("group2", "group3")));

        assertTrue(CollectionUtil.noneOf(Arrays.asList("group1", "group2"), "group3"));
        assertFalse(CollectionUtil.noneOf(Arrays.asList("group1", "group2"),"group2"));

        assertTrue(CollectionUtil.noneOf("group1, group2", "group3, group4"));
        assertFalse(CollectionUtil.noneOf("group1, group2", "group1, group2"));
        assertFalse(CollectionUtil.noneOf("group1, group2", "group2, group3"));

        ObjectMapper mapper = new ObjectMapper();
        assertTrue(CollectionUtil.noneOf(mapper.valueToTree(Arrays.asList("group1", "group2")), mapper.valueToTree(Arrays.asList("group3", "group4"))));
        assertFalse(CollectionUtil.noneOf(mapper.valueToTree(Arrays.asList("group1", "group2")), mapper.valueToTree(Arrays.asList("group1", "group2"))));
        assertFalse(CollectionUtil.noneOf(mapper.valueToTree(Arrays.asList("group1", "group2")), mapper.valueToTree(Arrays.asList("group2", "group3"))));
    }