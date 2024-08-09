@Test
    public void removeDuplicate(){
        List<String> list = new ArrayList<String>();
        list.add("feilong1");
        list.add("feilong2");
        list.add("feilong2");
        list.add("feilong3");

        LOGGER.info(JsonUtil.format(CollectionsUtil.removeDuplicate(list)));
        LOGGER.info(JsonUtil.format(CollectionsUtil.removeDuplicate(null)));
    }