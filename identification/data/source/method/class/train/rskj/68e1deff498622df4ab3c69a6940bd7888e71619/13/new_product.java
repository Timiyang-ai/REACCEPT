public boolean vote(ABICallSpec callSpec, TxSender voter) {
        if (!authorizer.isAuthorized(voter))
            return false;

        if (!votes.containsKey(callSpec)) {
            votes.put(callSpec, new ArrayList<>());
        }

        List<TxSender> callVoters = votes.get(callSpec);

        if (callVoters.contains(voter))
            return false;

        callVoters.add(voter);
        return true;
    }