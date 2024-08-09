@Test
    public void testRemoveDuplicate(){
        List<String> list = ConvertUtil.toList("feilong1", "feilong2", "feilong2", "feilong3");

        List<String> removeDuplicate = CollectionsUtil.removeDuplicate(list);

        assertSame(3, removeDuplicate.size());
        assertThat(removeDuplicate, hasItems("feilong1", "feilong2", "feilong3"));

        assertSame(4, list.size());
        assertThat(list, hasItems("feilong1", "feilong2", "feilong2", "feilong3"));

        assertEquals(emptyList(), CollectionsUtil.removeDuplicate(null));
    }