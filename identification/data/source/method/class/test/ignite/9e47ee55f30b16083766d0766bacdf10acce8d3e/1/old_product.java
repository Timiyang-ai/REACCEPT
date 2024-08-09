private void iterateConcurrentPutRemove() throws Exception {
        final TestTree tree = createTestTree(true);

        final int KEYS = 10_000;

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        for (int i = 0; i < 10; i++) {
            for (long idx = 0L; idx < KEYS; ++idx)
                tree.put(idx);

            final Long findKey;

            if (MAX_PER_PAGE > 0) {
                switch (i) {
                    case 0:
                        findKey = 1L;

                        break;

                    case 1:
                        findKey = (long)MAX_PER_PAGE;

                        break;

                    case 2:
                        findKey = (long)MAX_PER_PAGE - 1;

                        break;

                    case 3:
                        findKey = (long)MAX_PER_PAGE + 1;

                        break;

                    case 4:
                        findKey = (long)(KEYS / MAX_PER_PAGE / 2) * MAX_PER_PAGE;

                        break;

                    case 5:
                        findKey = (long)(KEYS / MAX_PER_PAGE / 2) * MAX_PER_PAGE - 1;

                        break;

                    case 6:
                        findKey = (long)(KEYS / MAX_PER_PAGE / 2) * MAX_PER_PAGE + 1;

                        break;

                    case 7:
                        findKey = (long)KEYS - 1;

                        break;

                    default:
                        findKey = rnd.nextLong(KEYS);
                }
            }
            else
                findKey = rnd.nextLong(KEYS);

            info("Iteration [iter=" + i + ", key=" + findKey + ']');

            assertEquals(findKey, tree.findOne(findKey));
            checkIterate(tree, findKey, findKey, findKey, true);

            IgniteInternalFuture getFut = GridTestUtils.runMultiThreadedAsync(new Callable<Void>() {
                @Override public Void call() throws Exception {
                    ThreadLocalRandom rnd = ThreadLocalRandom.current();

                    TestTreeRowClosure p = new TestTreeRowClosure(findKey);

                    TestTreeRowClosure falseP = new TestTreeRowClosure(-1L);

                    int cnt = 0;

                    while (!stop.get()) {
                        int shift = MAX_PER_PAGE > 0 ? rnd.nextInt(MAX_PER_PAGE * 2) : rnd.nextInt(100);

                        checkIterateC(tree, findKey, findKey, p, true);

                        checkIterateC(tree, findKey - shift, findKey, p, true);

                        checkIterateC(tree, findKey - shift, findKey + shift, p, true);

                        checkIterateC(tree, findKey, findKey + shift, p, true);

                        checkIterateC(tree, -100L, KEYS + 100L, falseP, false);

                        cnt++;
                    }

                    info("Done, read count: " + cnt);

                    return null;
                }
            }, 10, "find");

            asyncRunFut = new GridCompoundFuture<>();

            asyncRunFut.add(getFut);

            asyncRunFut.markInitialized();

            try {
                U.sleep(100);

                for (int j = 0; j < 20; j++) {
                    for (long idx = 0L; idx < KEYS / 2; ++idx) {
                        long toRmv = rnd.nextLong(KEYS);

                        if (toRmv != findKey)
                            tree.remove(toRmv);
                    }

                    for (long idx = 0L; idx < KEYS / 2; ++idx) {
                        long put = rnd.nextLong(KEYS);

                        tree.put(put);
                    }
                }
            }
            finally {
                stop.set(true);
            }

            asyncRunFut.get();

            stop.set(false);
        }
    }