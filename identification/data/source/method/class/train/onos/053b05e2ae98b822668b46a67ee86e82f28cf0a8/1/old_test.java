    @Test
    public void testdeleteLsp() throws Exception {
        defaultIsisLsdb.deleteLsp(lsPdu);
        assertThat(defaultIsisLsdb, is(notNullValue()));

        lsPdu.setIsisPduType(IsisPduType.L2LSPDU.value());
        defaultIsisLsdb.deleteLsp(lsPdu);
        assertThat(defaultIsisLsdb, is(notNullValue()));

        lsPdu.setIsisPduType(IsisPduType.L1CSNP.value());
        defaultIsisLsdb.deleteLsp(lsPdu);
        assertThat(defaultIsisLsdb, is(notNullValue()));
    }