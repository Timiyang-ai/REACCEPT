public boolean vote(ABICallSpec callSpec, ECKey voter) {
        if (!authorizer.isAuthorized(voter))
            return false;

        List<ECKey> callVotes = votes.getOrDefault(callSpec, new ArrayList<>());
        if (callVotes.contains(voter))
            return false;

        callVotes.add(voter);
        return true;
    }