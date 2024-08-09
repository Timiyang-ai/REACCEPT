@Test
    public void hugeFolder() throws Exception {
        String username = generateUsername();
        String password = "test01";
        UserContext context = ensureSignedUp(username, password, network, crypto);
        FileWrapper userRoot = context.getUserRoot().get();
        List<String> names = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> names.add(randomString()));

        long worst = 0, best = Long.MAX_VALUE, start = System.currentTimeMillis();
        for (int i=0; i < names.size(); i++) {
            String filename = names.get(i);
            long t1 = System.currentTimeMillis();
            userRoot = userRoot.mkdir(filename, context.network, false, crypto).join();
            long duration = System.currentTimeMillis() - t1;
            worst = Math.max(worst, duration);
            best = Math.min(best, duration);
            System.err.printf("MKDIR(%d) duration: %d mS, best: %d mS, worst: %d mS, av: %d mS\n", i,
                    duration, best, worst, (t1 + duration - start) / (i + 1));
        }

        long worstRead = 0, bestRead = Long.MAX_VALUE, startRead = System.currentTimeMillis();
        for (int i=0; i < 100; i++) {
            long t1 = System.currentTimeMillis();
            context.getByPath(Paths.get(username, names.get(random.nextInt(names.size())))).join();
            long duration = System.currentTimeMillis() - t1;
            worstRead = Math.max(worstRead, duration);
            bestRead = Math.min(bestRead, duration);
            System.err.printf("GetByPath(%d) duration: %d mS, best: %d mS, worst: %d mS, av: %d mS\n", i,
                    duration, bestRead, worstRead, (t1 + duration - startRead) / (i + 1));
        }
    }