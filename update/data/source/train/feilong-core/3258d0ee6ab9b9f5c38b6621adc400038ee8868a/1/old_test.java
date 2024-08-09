@Test
    public void testReadPropertiesToAliasBean(){
        DangaMemCachedConfig dangaMemCachedConfig = ResourceBundleUtil
                        .readPropertiesToAliasBean("messages.memcached", DangaMemCachedConfig.class);
        LOGGER.debug(JsonUtil.format(dangaMemCachedConfig));
    }