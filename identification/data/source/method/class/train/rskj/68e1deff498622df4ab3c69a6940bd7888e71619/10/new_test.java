    @Test
    public void vote_unauthorized() {
        ABICallSpec spec_fnc = new ABICallSpec("fn-c", new byte[][]{});
        Assert.assertFalse(election.vote(spec_fnc, createVoter("112233")));
        Assert.assertEquals(2, election.getVotes().size());
        Assert.assertEquals(0, election.getVotes().get(spec_fna).size());
        Assert.assertEquals(2, election.getVotes().get(spec_fnb).size());
        Assert.assertNull(election.getVotes().get(spec_fnc));
    }