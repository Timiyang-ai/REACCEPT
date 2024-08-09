@Test
    public void toBean(){
        String json = "{'data':[{'name':'get'},{'name':'set'}],'id':5}";
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        classMap.put("data", Person.class);

        MyBean myBean = JsonUtil.toBean(json, MyBean.class, classMap);
        LOGGER.info(JsonUtil.format(myBean));
    }