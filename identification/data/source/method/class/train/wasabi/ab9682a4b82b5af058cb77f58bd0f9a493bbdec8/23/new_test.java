    @Test
    public void deleteBucket() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserInfo(USER)).thenReturn(USERINFO);
        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        experimentsResource.deleteBucket(experiment.getID(), bucket.getLabel(), AUTHHEADER);
        verify(buckets).deleteBucket(experiment.getID(), bucket.getLabel(), USERINFO);
    }