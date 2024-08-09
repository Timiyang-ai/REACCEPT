public static byte[] serializeElection(ABICallElection election) {
        byte[][] bytes = new byte[election.getVotes().size() * 2][];
        int n = 0;

        Map<ABICallSpec, List<ECKey>> votes = election.getVotes();
        ABICallSpec[] specs = votes.keySet().toArray(new ABICallSpec[0]);
        Arrays.sort(specs, ABICallSpec.byBytesComparator);

        for (ABICallSpec spec : specs) {
            bytes[n++] = serializeABICallSpec(spec);
            bytes[n++] = serializePublicKeys(votes.get(spec));
        }

        return RLP.encodeList(bytes);
    }