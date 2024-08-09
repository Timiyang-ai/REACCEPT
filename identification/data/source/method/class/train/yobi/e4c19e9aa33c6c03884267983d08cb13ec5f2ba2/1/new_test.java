    @Test
    public void toNextState(){
        //Given
        State exptected = issue.nextState();

        //When
        issue.toNextState();

        //Then
        assertThat(issue.state).isEqualTo(exptected);
    }