    public void scrutinize(Snak snak) {
        Claim claim = Datamodel.makeClaim(TestingData.existingId, snak, Collections.emptyList());
        Statement statement = Datamodel.makeStatement(claim, Collections.emptyList(), StatementRank.NORMAL, "");
        scrutinize(statement);
    }