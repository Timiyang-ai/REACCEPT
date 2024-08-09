public boolean vote(ABICallSpec callSpec, ECKey voter) {
        if (!authorizer.isAuthorized(voter))
            return false;

        if (!votes.containsKey(callSpec)) {
            votes.put(callSpec, new ArrayList<>());
        }

        List<ECKey> callVotes = votes.get(callSpec);

        if (callVotes.contains(voter))
            return false;

        callVotes.add(voter);
        return true;
    }