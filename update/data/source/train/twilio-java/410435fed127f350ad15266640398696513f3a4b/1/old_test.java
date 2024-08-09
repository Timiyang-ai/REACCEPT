@Test
    public void testSetMaxSize() throws TwilioRestException {

        setupMocks();
        stub(
                client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
                        + ".json"), Matchers.eq("POST"), Matchers.any(Map.class)))
                .toReturn(resp);
        Queue q = new Queue(client, queueSid);
        q.setRequestAccountSid(accountSid);
        q.setMaxSize(99);
        assertTrue(q.getMaxSize() == 99);
    }