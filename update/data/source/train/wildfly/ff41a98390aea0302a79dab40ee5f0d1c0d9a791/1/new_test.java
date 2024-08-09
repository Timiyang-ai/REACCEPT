@Test
    public void testGetConnection1() throws Throwable {
        assertNotNull(connectionFactory1);
        AnnoConnectionImpl connection1 = (AnnoConnectionImpl) connectionFactory1
                .getConnection();
        assertNotNull(connection1);
        AnnoManagedConnectionFactory mcf = connection1.getMCF();
        assertNotNull(mcf);
        log.trace("MCF:" + mcf + "//1//" + mcf.getFirst() + "//2//"
                + mcf.getSecond());
        assertEquals((byte) 4, (byte) mcf.getFirst());
        assertEquals((short) 0, (short) mcf.getSecond());
        connection1.close();
    }