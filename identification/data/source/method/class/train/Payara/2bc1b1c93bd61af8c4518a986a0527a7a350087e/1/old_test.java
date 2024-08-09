    @Test
    public void convertStringToObjectTest() throws Exception {
        DummyCommand dc = new DummyCommand();
        Class<?> cl = dc.getClass();
        AnnotatedElement target = (AnnotatedElement)cl.getDeclaredField("foo");
        String paramValueStr = "prop1=valA:prop2=valB:prop3=valC";
        Object paramValActual = MapInjectionResolver.convertStringToObject(
                                    target, String.class, paramValueStr);
        Object paramValExpected =  "prop1=valA:prop2=valB:prop3=valC";
        assertEquals("String type", paramValExpected, paramValActual);
  
        target = (AnnotatedElement)cl.getDeclaredField("prop");
        paramValActual = MapInjectionResolver.convertStringToObject(
                                    target, Properties.class, paramValueStr);
        paramValExpected = new Properties();        
        ((Properties)paramValExpected).put("prop1", "valA");
        ((Properties)paramValExpected).put("prop2", "valB");
        ((Properties)paramValExpected).put("prop3", "valC");
        assertEquals("Properties type", paramValExpected, paramValActual);

        target = (AnnotatedElement)cl.getDeclaredField("portnum");
        paramValueStr = "8080";
        paramValActual = MapInjectionResolver.convertStringToObject(
                                    target, Integer.class, paramValueStr);
        paramValExpected = new Integer(8080);
        assertEquals("Integer type", paramValExpected, paramValActual);

        paramValueStr = "server1:server2:server3";
        target = (AnnotatedElement)cl.getDeclaredField("lstr");
        paramValActual = MapInjectionResolver.convertStringToObject(
                                    target, List.class, paramValueStr);
        List<String> paramValueList = new java.util.ArrayList();
        paramValueList.add("server1");
        paramValueList.add("server2");
        paramValueList.add("server3");
        assertEquals("List type", paramValueList, paramValActual);

        paramValueStr = "server1,server2,server3";
        target = (AnnotatedElement)cl.getDeclaredField("astr");
        paramValActual = MapInjectionResolver.convertStringToObject(
                                    target, (new String[]{}).getClass(),
                                                  paramValueStr);
        String[] strArray = new String[3];
        strArray[0] = "server1";
        strArray[1] = "server2";
        strArray[2] = "server3";
        assertEquals("String Array type", strArray, (String[])paramValActual);
    }