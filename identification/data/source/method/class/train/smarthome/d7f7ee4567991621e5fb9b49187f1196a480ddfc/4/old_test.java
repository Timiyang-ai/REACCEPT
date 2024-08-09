    @Test
    public void setState_stateUndef() {
        RollershutterItem sut = new RollershutterItem("Test");
        StateUtil.testUndefStates(sut);
    }