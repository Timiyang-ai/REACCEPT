@Test
    public void testBootstrap7() throws Exception {
        final Random rnd = new Random(42);
        Peer peer = null;
        try {
            peer = new PeerMaker(new Number160(rnd)).ports(4000).makeAndListen();

            Collection<PeerAddress> bootstrapTo = new ArrayList<PeerAddress>(2);
            PeerAddress pa = new PeerAddress(new Number160(rnd), "192.168.77.77", 4000, 4000);
            bootstrapTo.add(peer.getPeerAddress());
            bootstrapTo.add(pa);
            FutureBootstrap tmp = peer.bootstrap().setBootstrapTo(bootstrapTo).start();
            tmp.awaitUninterruptibly();
            Assert.assertEquals(false, tmp.isSuccess());
        } finally {
            if (peer != null) {
                peer.shutdown().await();
            }
        }
    }