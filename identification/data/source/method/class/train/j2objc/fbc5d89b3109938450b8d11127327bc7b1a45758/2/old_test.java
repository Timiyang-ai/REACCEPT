    public void test_toString() throws Exception {
        final String fixture = "0123456789";
        StringBuilder sb = new StringBuilder(fixture);
        assertEquals(fixture, sb.toString());

        sb.setLength(0);
        sb.append("abcde");
        assertEquals("abcde", sb.toString());
        sb.setLength(1000);
        byte[] bytes = sb.toString().getBytes("GB18030");
        for (int i = 5; i < bytes.length; i++) {
            assertEquals(0, bytes[i]);
        }

        sb.setLength(5);
        sb.append("fghij");
        assertEquals("abcdefghij", sb.toString());
    }