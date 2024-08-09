    @Test
    public void startPunishment() throws InterruptedException {
        PeerScoring scoring = new PeerScoring();

        Assert.assertEquals(0, scoring.getPunishmentTime());
        Assert.assertEquals(0, scoring.getPunishmentCounter());

        scoring.startPunishment(1000);

        Assert.assertEquals(1, scoring.getPunishmentCounter());
        Assert.assertFalse(scoring.hasGoodReputation());
        Assert.assertEquals(1000, scoring.getPunishmentTime());
        TimeUnit.MILLISECONDS.sleep(10);
        Assert.assertFalse(scoring.hasGoodReputation());
        TimeUnit.MILLISECONDS.sleep(2000);
        Assert.assertTrue(scoring.hasGoodReputation());
        Assert.assertEquals(1, scoring.getPunishmentCounter());
    }