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
            userRoot.mkdir(filename, context.network, false, context.crypto.random).get();
            long duration = System.currentTimeMillis() - t1;
            worst = Math.max(worst, duration);
            best = Math.min(best, duration);
            System.err.printf("MKDIR(%d) duration: %d mS, best: %d mS, worst: %d mS, av: %d mS\n", i, duration, best, worst, (t1 + duration - start) / (i + 1));
        }
    }