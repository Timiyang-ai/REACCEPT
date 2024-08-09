@Test
    @SuppressWarnings("static-method")
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

        //        assertThat(newDynaBean, allOf(//
        //                        hasProperty("address", is((Object) new HashMap())),
        //                        hasProperty("firstName", is((Object) "Fred")),
        //                        hasProperty("lastName", is((Object) "Flintstone"))
        //
        //        //
        //        ));
    }