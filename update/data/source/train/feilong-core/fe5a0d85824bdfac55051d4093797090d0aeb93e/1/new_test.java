@Test
    public void testNewDynaBean(){
        Map<String, Object> map = newHashMap();

        DynaBean newDynaBean = BeanUtil.newDynaBean(
                        toMapUseEntrys(//
                                        of("address", (Object) map),
                                        of("firstName", (Object) "Fred"),
                                        of("lastName", (Object) "Flintstone")));

        assertEquals(map, newDynaBean.get("address"));
        assertEquals("Fred", newDynaBean.get("firstName"));
        assertEquals("Flintstone", newDynaBean.get("lastName"));
    }