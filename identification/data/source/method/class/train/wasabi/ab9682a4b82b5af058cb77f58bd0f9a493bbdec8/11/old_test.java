    @Test
    public void getAssignmentCountsTest() {
        AssignmentCounts assignmentCounts = mock(AssignmentCounts.class);
        Experiment experiment = mock(Experiment.class);
        Experiment.ID id = Experiment.ID.newInstance();
        when(experimentRepository.getExperiment(eq(id))).thenReturn(experiment);
        when(assignmentsRepository.getBucketAssignmentCount(eq(experiment))).thenReturn(assignmentCounts);
        //test the else part
        Date date = new Date(1000); //some time in 1970
        when(experiment.getCreationTime()).thenReturn(date);
        AssignmentCounts result = this.analyticsImpl.getAssignmentCounts(id, null);
        assertThat(result, is(assignmentCounts));
        //test the if part
        date = new Date(); //always now
        when(experiment.getCreationTime()).thenReturn(date);
        result = this.analyticsImpl.getAssignmentCounts(id, null);
        assertThat(result, is(assignmentCounts));
    }