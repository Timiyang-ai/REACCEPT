    @Test
    public void deleteNodeTest() throws InterruptedException, SmackException, IOException, XMPPException {
        ThreadedDummyConnection con = ThreadedDummyConnection.newInstance();
        PubSubManager mgr = new PubSubManager(con, DUMMY_PUBSUB_SERVICE);

        mgr.deleteNode("foo@bar.org");

        PubSub pubSubDeleteRequest = con.getSentPacket();
        assertEquals("http://jabber.org/protocol/pubsub#owner", pubSubDeleteRequest.getChildElementNamespace());
        assertEquals("pubsub", pubSubDeleteRequest.getChildElementName());
    }