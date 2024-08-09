@Test
    public void testMkDir() {
        long reqId = 14;

        String path1 = "/a/b/test_mk_dir";
        BinaryRawWriterEx message = createMessage(reqId, ModelStorateThinClientProcessor.PROCESSOR_ID, ModelStorateThinClientProcessor.Method.MKDIR);
        message.writeString(path1);
        message.writeBoolean(false);
        ClientCustomQueryRequest req = new ClientCustomQueryRequest(toReader(message));
        ClientResponse resp = req.process(connCtx);
        BinaryRawWriterEx out = createWriter();
        resp.encode(connCtx, out);
        byte[] result = out.out().arrayCopy();

        assertTrue(ms.exists(path1));
        assertArrayEquals(getExpectedMessageHeader(reqId).out().arrayCopy(), result);
    }