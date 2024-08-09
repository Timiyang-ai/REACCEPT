private void updateSingleValue(boolean singleNode, final boolean locQry) throws Exception {
        fail("https://issues.apache.org/jira/browse/IGNITE-9470");

        final int VALS = 100;

        final int writers = 4;

        final int readers = 4;

        final int INC_BY = 110;

        final IgniteInClosure<IgniteCache<Object, Object>> init = new IgniteInClosure<IgniteCache<Object, Object>>() {
            @Override public void apply(IgniteCache<Object, Object> cache) {
                Map<Integer, MvccTestSqlIndexValue> vals = new HashMap<>();

                for (int i = 0; i < VALS; i++)
                    vals.put(i, new MvccTestSqlIndexValue(i));

                cache.putAll(vals);
            }
        };

        GridInClosure3<Integer, List<TestCache>, AtomicBoolean> writer =
            new GridInClosure3<Integer, List<TestCache>, AtomicBoolean>() {
                @Override public void apply(Integer idx, List<TestCache> caches, AtomicBoolean stop) {
                    ThreadLocalRandom rnd = ThreadLocalRandom.current();

                    int cnt = 0;

                    while (!stop.get()) {
                        TestCache<Integer, MvccTestSqlIndexValue> cache = randomCache(caches, rnd);

                        try {
                            Integer key = rnd.nextInt(VALS);

                            cache.cache.invoke(key, new CacheEntryProcessor<Integer, MvccTestSqlIndexValue, Object>() {
                                @Override public Object process(MutableEntry<Integer, MvccTestSqlIndexValue> e, Object... args) {
                                    Integer key = e.getKey();

                                    MvccTestSqlIndexValue val = e.getValue();

                                    int newIdxVal;

                                    if (val.idxVal1 < INC_BY) {
                                        assertEquals(key.intValue(), val.idxVal1);

                                        newIdxVal = val.idxVal1 + INC_BY;
                                    }
                                    else {
                                        assertEquals(INC_BY + key, val.idxVal1);

                                        newIdxVal = key;
                                    }

                                    e.setValue(new MvccTestSqlIndexValue(newIdxVal));

                                    return null;
                                }
                            });
                        }
                        finally {
                            cache.readUnlock();
                        }
                    }

                    info("Writer finished, updates: " + cnt);
                }
            };

        GridInClosure3<Integer, List<TestCache>, AtomicBoolean> reader =
            new GridInClosure3<Integer, List<TestCache>, AtomicBoolean>() {
                @Override public void apply(Integer idx, List<TestCache> caches, AtomicBoolean stop) {
                    ThreadLocalRandom rnd = ThreadLocalRandom.current();

                    List<SqlFieldsQuery> fieldsQrys = new ArrayList<>();

                    fieldsQrys.add(
                        new SqlFieldsQuery("select _key, idxVal1 from MvccTestSqlIndexValue where idxVal1=?").setLocal(locQry));

                    fieldsQrys.add(new SqlFieldsQuery("select _key, idxVal1 from MvccTestSqlIndexValue where idxVal1=? or idxVal1=?").setLocal(locQry));

                    fieldsQrys.add(new SqlFieldsQuery("select _key, idxVal1 from MvccTestSqlIndexValue where _key=?").setLocal(locQry));

                    List<SqlQuery<Integer, MvccTestSqlIndexValue>> sqlQrys = new ArrayList<>();

                    sqlQrys.add(new SqlQuery<Integer, MvccTestSqlIndexValue>(MvccTestSqlIndexValue.class, "idxVal1=?").setLocal(locQry));

                    sqlQrys.add(new SqlQuery<Integer, MvccTestSqlIndexValue>(MvccTestSqlIndexValue.class, "idxVal1=? or idxVal1=?").setLocal(locQry));

                    sqlQrys.add(new SqlQuery<Integer, MvccTestSqlIndexValue>(MvccTestSqlIndexValue.class, "_key=?").setLocal(locQry));

                    while (!stop.get()) {
                        Integer key = rnd.nextInt(VALS);

                        int qryIdx = rnd.nextInt(3);

                        TestCache<Integer, MvccTestSqlIndexValue> cache = randomCache(caches, rnd);

                        List<List<?>> res;

                        try {
                            if (rnd.nextBoolean()) {
                                SqlFieldsQuery qry = fieldsQrys.get(qryIdx);

                                if (qryIdx == 1)
                                    qry.setArgs(key, key + INC_BY);
                                else
                                    qry.setArgs(key);

                                res = cache.cache.query(qry).getAll();
                            }
                            else {
                                SqlQuery<Integer, MvccTestSqlIndexValue> qry = sqlQrys.get(qryIdx);

                                if (qryIdx == 1)
                                    qry.setArgs(key, key + INC_BY);
                                else
                                    qry.setArgs(key);

                                res = new ArrayList<>();

                                for (IgniteCache.Entry<Integer, MvccTestSqlIndexValue> e : cache.cache.query(qry).getAll()) {
                                    List<Object> row = new ArrayList<>(2);

                                    row.add(e.getKey());
                                    row.add(e.getValue().idxVal1);

                                    res.add(row);
                                }
                            }
                        }
                        finally {
                            cache.readUnlock();
                        }

                        assertTrue(qryIdx == 0 || !res.isEmpty());

                        if (!res.isEmpty()) {
                            assertEquals(1, res.size());

                            List<?> resVals = res.get(0);

                            Integer key0 = (Integer)resVals.get(0);
                            Integer val0 = (Integer)resVals.get(1);

                            assertEquals(key, key0);
                            assertTrue(val0.equals(key) || val0.equals(key + INC_BY));
                        }
                    }

                    if (idx == 0) {
                        SqlFieldsQuery qry = new SqlFieldsQuery("select _key, idxVal1 from MvccTestSqlIndexValue");

                        TestCache<Integer, MvccTestSqlIndexValue> cache = randomCache(caches, rnd);

                        List<List<?>> res;

                        try {
                            res = cache.cache.query(qry).getAll();
                        }
                        finally {
                            cache.readUnlock();
                        }

                        assertEquals(VALS, res.size());

                        for (List<?> vals : res)
                            info("Value: " + vals);
                    }
                }
            };

        int srvs;
        int clients;

        if (singleNode) {
            srvs = 1;
            clients = 0;
        }
        else {
            srvs = 4;
            clients = 2;
        }

        readWriteTest(
            null,
            srvs,
            clients,
            0,
            DFLT_PARTITION_COUNT,
            writers,
            readers,
            DFLT_TEST_TIME,
            new InitIndexing(Integer.class, MvccTestSqlIndexValue.class),
            init,
            writer,
            reader);

        for (Ignite node : G.allGrids())
            checkActiveQueriesCleanup(node);
    }