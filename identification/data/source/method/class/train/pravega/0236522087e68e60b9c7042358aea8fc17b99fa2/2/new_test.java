    @Test
    public void createStreamTests() {
        final ScalingPolicy policy1 = ScalingPolicy.fixed(2);
        final ScalingPolicy policy2 = ScalingPolicy.fixed(3);
        final StreamConfiguration configuration1 = StreamConfiguration.builder().scalingPolicy(policy1).build();
        final StreamConfiguration configuration2 = StreamConfiguration.builder().scalingPolicy(policy2).build();
        final StreamConfiguration configuration3 = StreamConfiguration.builder().scalingPolicy(policy2).build();

        CreateStreamStatus status;

        // region checkStream
        ResultObserver<CreateScopeStatus> result = new ResultObserver<>();
        this.controllerService.createScope(ScopeInfo.newBuilder().setScope(SCOPE1).build(), result);
        Assert.assertEquals(result.get().getStatus(), CreateScopeStatus.Status.SUCCESS);

        ResultObserver<CreateStreamStatus> result1 = new ResultObserver<>();
        this.controllerService.createStream(ModelHelper.decode(SCOPE1, STREAM1, configuration1), result1);
        status = result1.get();
        Assert.assertEquals(status.getStatus(), CreateStreamStatus.Status.SUCCESS);

        ResultObserver<CreateStreamStatus> result2 = new ResultObserver<>();
        this.controllerService.createStream(ModelHelper.decode(SCOPE1, STREAM2, configuration2), result2);
        status = result2.get();
        Assert.assertEquals(status.getStatus(), CreateStreamStatus.Status.SUCCESS);
        // endregion

        // region duplicate create stream
        ResultObserver<CreateStreamStatus> result3 = new ResultObserver<>();
        this.controllerService.createStream(ModelHelper.decode(SCOPE1, STREAM1, configuration1), result3);
        status = result3.get();
        Assert.assertEquals(status.getStatus(), CreateStreamStatus.Status.STREAM_EXISTS);
        // endregion

        // create stream for non-existent scope
        ResultObserver<CreateStreamStatus> result4 = new ResultObserver<>();
        this.controllerService.createStream(ModelHelper.decode("SCOPE3", STREAM2, configuration3), result4);
        status = result4.get();
        Assert.assertEquals(status.getStatus(), CreateStreamStatus.Status.SCOPE_NOT_FOUND);

        //create stream with invalid stream name "abc/def"
        ResultObserver<CreateStreamStatus> result5 = new ResultObserver<>();
        final StreamConfiguration configuration4 =
                StreamConfiguration.builder().scalingPolicy(policy2).build();
        this.controllerService.createStream(ModelHelper.decode("SCOPE3", "abc/def", configuration4), result5);
        status = result5.get();
        assertEquals(status.getStatus(), CreateStreamStatus.Status.INVALID_STREAM_NAME);

        // Create stream with an internal stream name.
        ResultObserver<CreateStreamStatus> result6 = new ResultObserver<>();
        final StreamConfiguration configuration6 =
                StreamConfiguration.builder().scalingPolicy(policy2).build();
        this.controllerService.createStream(ModelHelper.decode(SCOPE1, "abcdef", configuration6), result6);
        status = result6.get();
        assertEquals(status.getStatus(), CreateStreamStatus.Status.SUCCESS);
    }