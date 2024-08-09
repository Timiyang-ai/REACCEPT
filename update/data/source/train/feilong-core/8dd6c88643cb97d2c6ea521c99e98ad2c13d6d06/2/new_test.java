@Test
    public void toMap(){
        String json = "{'data1':{'name':'get'},'data2':{'name':'set'},'null':{'name':'set'}}";
        Map<String, Person> map = JsonUtil.toMap(json, Person.class);
        LOGGER.info(JsonUtil.toJSON(map).toString(4, 4));
    }