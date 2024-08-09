public boolean grantVote(RaftMember address) {
        return voters.add(address);
    }