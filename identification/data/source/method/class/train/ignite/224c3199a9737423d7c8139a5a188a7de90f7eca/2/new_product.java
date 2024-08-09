private void joinTransactional(boolean singleNode, final boolean distributedJoin) throws Exception {
        final int KEYS = 100;

        final int writers = 4;

        final int readers = 4;

        GridInClosure3<Integer, List<TestCache>, AtomicBoolean> writer =
            new GridInClosure3<Integer, List<TestCache>, AtomicBoolean>() {
                @Override public void apply(Integer idx, List<TestCache> caches, AtomicBoolean stop) {
                    ThreadLocalRandom rnd = ThreadLocalRandom.current();

                    int cnt = 0;

                    while (!stop.get()) {
                        TestCache<Object, Object> cache = randomCache(caches, rnd);

                        IgniteTransactions txs = cache.cache.unwrap(Ignite.class).transactions();

                        try {
                            while (true) {
                                try (Transaction tx = txs.txStart(PESSIMISTIC, REPEATABLE_READ)) {
                                    Integer key = rnd.nextInt(KEYS);

                                    JoinTestChildKey childKey = new JoinTestChildKey(key);

                                    JoinTestChild child = (JoinTestChild)cache.cache.get(childKey);

                                    if (child == null) {
                                        int parentKey = distributedJoin ? key + 100 : key;

                                        child = new JoinTestChild(parentKey);

                                        cache.cache.put(childKey, child);

                                        JoinTestParent parent = new JoinTestParent(parentKey);

                                        cache.cache.put(new JoinTestParentKey(parentKey), parent);
                                    }
                                    else {
                                        cache.cache.remove(childKey);

                                        cache.cache.remove(new JoinTestParentKey(child.parentId));
                                    }

                                    tx.commit();

                                    break;
                                }
                                catch (CacheException e) {
                                    MvccFeatureChecker.assertMvccWriteConflict(e);
                                }
                            }

                            cnt++;
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

                    List<SqlFieldsQuery> qrys = new ArrayList<>();

                    qrys.add(new SqlFieldsQuery("select c.parentId, p.id from " +
                        "JoinTestChild c left outer join JoinTestParent p on (c.parentId = p.id)").
                        setDistributedJoins(distributedJoin));

                    qrys.add(new SqlFieldsQuery("select c.parentId, p.id from " +
                        "JoinTestChild c left outer join JoinTestParent p on (c.parentId = p.id) where p.id = 10").
                        setDistributedJoins(distributedJoin));

                    qrys.add(new SqlFieldsQuery("select c.parentId, p.id from " +
                        "JoinTestChild c left outer join JoinTestParent p on (c.parentId = p.id) where p.id != 10").
                        setDistributedJoins(distributedJoin));

                    while (!stop.get()) {
                        TestCache<Object, Object> cache = randomCache(caches, rnd);

                        try {
                            for (SqlFieldsQuery qry : qrys) {
                                List<List<?>> res = cache.cache.query(qry).getAll();

                                if (!res.isEmpty()) {
                                    for (List<?> resRow : res) {
                                        Integer parentId = (Integer)resRow.get(1);

                                        assertNotNull(parentId);
                                    }
                                }
                            }
                        }
                        finally {
                            cache.readUnlock();
                        }
                    }

                    if (idx == 0) {
                        TestCache<Object, Object> cache = randomCache(caches, rnd);

                        try {
                            List<List<?>> res = cache.cache.query(qrys.get(0)).getAll();

                            info("Reader finished, result: " + res);
                        }
                        finally {
                            cache.readUnlock();
                        }
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
            new InitIndexing(JoinTestParentKey.class, JoinTestParent.class,
                JoinTestChildKey.class, JoinTestChild.class),
            null,
            writer,
            reader);
    }