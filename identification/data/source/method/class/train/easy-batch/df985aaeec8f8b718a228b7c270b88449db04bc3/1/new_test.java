    @Test
    public void execute() {
        //when
        JobReport actual = jobExecutor.execute(job);

        //then
        assertThat(actual).isEqualTo(report);
    }