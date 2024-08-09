public boolean vote(ABICallSpec callSpec, ABICallVoter voter) {
        if (!authorizer.isAuthorized(voter))
            return false;

        if (!votes.containsKey(callSpec)) {
            votes.put(callSpec, new ArrayList<>());
        }

        List<ABICallVoter> callVoters = votes.get(callSpec);

        if (callVoters.contains(voter))
            return false;

        callVoters.add(voter);
        return true;
    }