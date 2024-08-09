@Test
    public void testFindByProperties() {
        List list = Collections.singletonList(instance);
        Map props = Collections.singletonMap("name", "abc");
        when(repository.findByProperties(MyEntity.class, MapParameters.create(props))).thenReturn(list);
        assertEquals(list, AbstractEntity.findByProperties(MyEntity.class, props));
    }