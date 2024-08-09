    @Test
    public void convertListToObjectTest() throws Exception {
        DummyCommand dc = new DummyCommand();
        Class<?> cl = dc.getClass();
        AnnotatedElement target =
            (AnnotatedElement)cl.getDeclaredField("propm");
        List<String> paramValueList = new ArrayList<String>();
        paramValueList.add("prop1=valA");
        paramValueList.add("prop2=valB");
        paramValueList.add("prop3=valC");
        Object paramValActual = MapInjectionResolver.convertListToObject(
                                    target, Properties.class, paramValueList);
        Object paramValExpected = new Properties();        
        ((Properties)paramValExpected).put("prop1", "valA");
        ((Properties)paramValExpected).put("prop2", "valB");
        ((Properties)paramValExpected).put("prop3", "valC");
        assertEquals("Properties type", paramValExpected, paramValActual);

        paramValueList.clear();
        paramValueList.add("server1");
        paramValueList.add("server2");
        paramValueList.add("server3");
        target = (AnnotatedElement)cl.getDeclaredField("lstrm");
        paramValActual = MapInjectionResolver.convertListToObject(
                                    target, List.class, paramValueList);
        assertEquals("List type", paramValueList, paramValActual);

        target = (AnnotatedElement)cl.getDeclaredField("astrm");
        paramValActual = MapInjectionResolver.convertListToObject(
                                    target, (new String[]{}).getClass(),
                                                  paramValueList);
        String[] strArray = new String[3];
        strArray[0] = "server1";
        strArray[1] = "server2";
        strArray[2] = "server3";
        assertEquals("String Array type", strArray, (String[])paramValActual);
    }