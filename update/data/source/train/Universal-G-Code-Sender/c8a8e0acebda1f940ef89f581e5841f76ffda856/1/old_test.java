@Test
    public void testQueueStringForComm() {
        System.out.println("queueStringForComm");
        String input = "input";
        instance.queueStringForComm(input);
        assertEquals(input, cb.getFirst());
    }