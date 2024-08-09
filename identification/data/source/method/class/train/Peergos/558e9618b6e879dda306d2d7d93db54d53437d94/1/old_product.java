public void hugeFolder() throws Exception {
        String username = generateUsername();
        String password = "test01";
        UserContext context = PeergosNetworkUtils.ensureSignedUp(username, password, network, crypto);
        FileTreeNode userRoot = context.getUserRoot().get();
        List<String> names = new ArrayList<>();
        IntStream.range(0, 2000).forEach(i -> names.add(randomString()));

        for (String filename: names) {
            userRoot.mkdir(filename, context.network, false, context.crypto.random);
        }
    }