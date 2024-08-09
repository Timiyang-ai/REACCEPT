@Test
    public void testRemove(){
        List<String> list = new ArrayList<String>(){

            {
                add("xinge");
                add("feilong1");
                add("feilong2");
                add("feilong2");
            }
        };

        List<String> removeList = CollectionsUtil.remove(list, "feilong2");
        assertArrayEquals(ConvertUtil.toArray("xinge", "feilong1"), ConvertUtil.toArray(removeList, String.class));
        assertArrayEquals(ConvertUtil.toArray("xinge", "feilong1", "feilong2", "feilong2"), ConvertUtil.toArray(list, String.class));
    }