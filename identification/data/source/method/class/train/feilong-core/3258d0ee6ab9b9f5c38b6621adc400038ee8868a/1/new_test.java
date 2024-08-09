@Test
    public void testReadToAliasBean(){
        DangaMemCachedConfig dangaMemCachedConfig = ResourceBundleUtil.readToAliasBean("messages.memcached", DangaMemCachedConfig.class);
        LOGGER.debug(JsonUtil.format(dangaMemCachedConfig));
    }