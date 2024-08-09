@Test
    public void toMap(){
        Map<String, Person> map = JsonUtil.toMap("{'data1':{'name':'get'},'data2':{'name':'set'}}", Person.class);
        LOGGER.info(JsonUtil.format(map));

        Map<String, String> map1 = JsonUtil.toMap("{'data1':{'name':'get'},'data2':{'name':'set'}}", String.class);
        LOGGER.info(JsonUtil.format(map1));
    }