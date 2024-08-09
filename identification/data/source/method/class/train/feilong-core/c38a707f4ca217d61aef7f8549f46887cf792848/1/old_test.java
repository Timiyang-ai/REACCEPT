@Test
    public final void testToIterator(){

        // *************************逗号分隔的数组********************************
        LOGGER.info(StringUtils.center("逗号分隔的数组", 60, "*"));
        Iterator<?> iterator = ObjectUtil.toIterator("1,2");
        printIterator(iterator);

        // ************************map*********************************
        LOGGER.info(StringUtils.center("map", 60, "*"));
        Map<String, String> map = new HashMap<String, String>();

        map.put("a", "1");
        map.put("b", "2");

        iterator = ObjectUtil.toIterator(map);
        printIterator(iterator);

        // ***************************array******************************
        LOGGER.info(StringUtils.center("array", 60, "*"));
        Object[] array = { "5", 8 };
        iterator = ObjectUtil.toIterator(array);
        printIterator(iterator);
        // ***************************collection******************************
        LOGGER.info(StringUtils.center("collection", 60, "*"));
        Collection<String> collection = new ArrayList<String>();
        collection.add("aaaa");
        collection.add("nnnnn");

        iterator = ObjectUtil.toIterator(collection);
        printIterator(iterator);

        // **********************enumeration***********************************
        LOGGER.info(StringUtils.center("enumeration", 60, "*"));
        Enumeration<Object> enumeration = new StringTokenizer("this is a test");
        LOGGER.debug(JsonUtil.format(ObjectUtil.toIterator(enumeration)));
    }