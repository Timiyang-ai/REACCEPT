    @Test
    public void getTermFor() {
        put(DID1, N1, true, true);
        assertEquals("wrong term", MastershipTerm.of(N1, 0), sms.getTermFor(DID1));

        //switch to N2 and back - 2 term switches
        sms.setMaster(N2, DID1);
        sms.setMaster(N1, DID1);
        assertEquals("wrong term", MastershipTerm.of(N1, 2), sms.getTermFor(DID1));
    }