public void hugeFolder() throws Exception {
        String username = generateUsername();
        String password = "test01";
        UserContext context = PeergosNetworkUtils.ensureSignedUp(username, password, network, crypto);
        List<String> names = new ArrayList<>();
        int nChildren = 2000;
        IntStream.range(0, nChildren).forEach(i -> names.add(randomString()));

        for (int i=0; i < names.size(); i++) {
            String filename = names.get(i);
            context.getUserRoot().get().mkdir(filename, context.network, false, context.crypto.random, hasher);
            Set<FileWrapper> children = context.getUserRoot().get().getChildren(context.network).get();
            Assert.assertTrue("All children present", children.size() == i + 3); // 3 due to .keystore and shared
        }
    }