private CacheConfiguration cacheConfiguration(String name, CacheAtomicityMode atomicityMode) {
        CacheConfiguration ccfg = new CacheConfiguration(DEFAULT_CACHE_NAME);

        ccfg.setName(name);
        ccfg.setCacheMode(PARTITIONED);
        ccfg.setWriteSynchronizationMode(FULL_SYNC);
        ccfg.setAtomicityMode(atomicityMode);
        ccfg.setBackups(1);

        QueryEntity qryEntity = new QueryEntity();

        qryEntity.setKeyType(TestKey.class.getName());
        qryEntity.setValueType(TestValue.class.getName());

        qryEntity.addQueryField("id", Integer.class.getName(), null);
        qryEntity.addQueryField("val", Integer.class.getName(), null);

        qryEntity.setKeyFields(Collections.singleton("id"));

        qryEntity.setIndexes(F.asList(new QueryIndex("id"), new QueryIndex("val")));

        ccfg.setQueryEntities(F.asList(qryEntity));

        return ccfg;
    }