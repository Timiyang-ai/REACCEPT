    @Test
    public void serializePendingFederation_serializedKeysAreCompressedAndThree() {
        final int NUM_MEMBERS = 10;
        final int EXPECTED_NUM_KEYS = 3;
        final int EXPECTED_PUBLICKEY_SIZE = 33;

        List<FederationMember> members = new ArrayList<>();
        for (int j = 0; j < NUM_MEMBERS; j++) {
            members.add(new FederationMember(new BtcECKey(), new ECKey(), new ECKey()));
        }

        PendingFederation testPendingFederation = new PendingFederation(members);

        byte[] serializedPendingFederation = BridgeSerializationUtils.serializePendingFederation(testPendingFederation);

        RLPList memberList = (RLPList) RLP.decode2(serializedPendingFederation).get(0);

        Assert.assertEquals(NUM_MEMBERS, memberList.size());

        for (int i = 0; i < NUM_MEMBERS; i++) {
            RLPList memberKeys = (RLPList) RLP.decode2(memberList.get(i).getRLPData()).get(0);
            Assert.assertEquals(EXPECTED_NUM_KEYS, memberKeys.size());
            for (int j = 0; j < EXPECTED_NUM_KEYS; j++) {
                Assert.assertEquals(EXPECTED_PUBLICKEY_SIZE, memberKeys.get(j).getRLPData().length);
            }

        }
    }