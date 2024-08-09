    @Test
    public void getErrorMessage_ReturnsErrorMessage() {

        // given
        Result result = features.get(1).getElements()[0].getSteps()[5].getResult();

        // when
        String errorMessage = result.getErrorMessage();

        // then
        assertThat(errorMessage).isEqualTo("java.lang.AssertionError: \n" +
                "Expected: is <80>\n" +
                "     got: <90>\n" +
                "\n" +
                "\tat org.junit.Assert.assertThat(Assert.java:780)\n" +
                "\tat org.junit.Assert.assertThat(Assert.java:738)\n" +
                "\tat net.masterthought.example.ATMScenario.checkBalance(ATMScenario.java:69)\n" +
                "\tat ✽.And the account balance should be 90(net/masterthought/example/ATMK.feature:12)\n");
    }