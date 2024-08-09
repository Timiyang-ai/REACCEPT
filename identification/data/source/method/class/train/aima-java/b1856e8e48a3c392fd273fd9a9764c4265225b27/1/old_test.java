    @Test
    public void resultTest() {
        State initState = new State("At(C1,SFO)^At(C2,JFK)^At(P1,SFO)^At(P2,JFK)" +
                "^Cargo(C1)^Cargo(C2)^Plane(P1)^Plane(P2)^Airport(JFK)^Airport(SFO)");
        State finalState = new State("At(C1,SFO)^At(C2,JFK)^At(P1,JFK)^At(P2,JFK)" +
                "^Cargo(C1)^Cargo(C2)^Plane(P1)^Plane(P2)^Airport(JFK)^Airport(SFO)");
        State newState = testState.result(flyActionTwo);
        Assert.assertNotEquals(finalState, newState);
        Assert.assertEquals(initState, newState);
        newState = testState.result(flyActionOne);
        Assert.assertEquals(finalState, newState);
        Assert.assertNotEquals(initState, newState);
        newState = testState.result(flyActionTwo);
        Assert.assertEquals(initState, newState);
    }