@Test
    public void replace(){
        String target = "/";
        Object replacement = "_";
        LOGGER.info(StringUtil.replace("黑色/黄色/蓝色", target, replacement));
    }