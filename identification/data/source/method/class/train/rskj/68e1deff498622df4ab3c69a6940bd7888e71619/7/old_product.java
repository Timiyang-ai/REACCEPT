public boolean vote(ABICallSpec callSpec, BtcECKey voter) {
        if (!authorizer.isAuthorized(voter))
            return false;

        List<BtcECKey> callVotes = votes.getOrDefault(callSpec, new ArrayList<>());
        if (callVotes.contains(voter))
            return false;

        callVotes.add(voter);
        return true;
    }