@Test(expected = Exception.class)
    public void testWriteToBuffer4() throws Exception {
        lsUpdate = new LsUpdate();
        lsUpdate.setAuthType(1);
        lsUpdate.setOspftype(3);
        lsUpdate.setRouterId(Ip4Address.valueOf("10.226.165.164"));
        lsUpdate.setAreaId(Ip4Address.valueOf("10.226.165.100"));
        lsUpdate.setChecksum(201);
        lsUpdate.setAuthentication(2);
        lsUpdate.setOspfPacLength(48);
        lsUpdate.setOspfVer(2);
        ospfMessageWriter.writeToBuffer(lsUpdate, 1, 1);
        assertThat(ospfMessageWriter, is(notNullValue()));
    }