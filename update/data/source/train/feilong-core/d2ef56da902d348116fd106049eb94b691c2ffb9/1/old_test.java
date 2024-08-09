@Test
    public void substring(){
        LOGGER.info(StringUtil.substring(TEXT, "jinxin".length()));
        String text1 = "Index: src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java";
        LOGGER.info(StringUtil.substring(text1, "Index: ".length()));
    }