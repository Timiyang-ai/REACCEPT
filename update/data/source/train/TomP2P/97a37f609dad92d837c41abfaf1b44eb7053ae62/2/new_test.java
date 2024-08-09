@Test
    public void testPeerAddress4() throws UnknownHostException {
        PeerAddress pa1 = Utils2.createPeerAddress(new Number256("0x857e35a42e444522456"),
                InetAddress.getByName("0123:4567:89ab:cdef:0123:4567:89ab:cdef"), RND.nextInt(BIT_16));
        PeerAddress pa2 = Utils2.createPeerAddress(new Number256("0x657435a424444522456"),
                InetAddress.getByName("f123:4567:89ab:cdef:0123:4567:89ab:cdef"), RND.nextInt(BIT_16));

        final int length = 200;
        byte[] me = new byte[length];
        final int offset = 50;
        int offset2 = pa1.encode(me, offset);
        pa2.encode(me, offset2);
        //
        Pair<PeerAddress, Integer> pair = PeerAddress.decode(me, offset); 
        PeerAddress pa3 = pair.element0();
        int offset4 = pair.element1();
        PeerAddress pa4 = PeerAddress.decode(me, offset4).element0();
        compare(pa1, pa3);
        compare(pa2, pa4);
    }