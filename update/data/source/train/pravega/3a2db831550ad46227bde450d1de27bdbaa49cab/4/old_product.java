@Before
    public void createStream() throws InterruptedException, ExecutionException {

        //create a scope
        Controller controller = getController();

        Boolean createScopeStatus = controller.createScope(SCOPE).get();
        log.debug("create scope status {}", createScopeStatus);

        //create a stream
        Boolean createStreamStatus = controller.createStream(CONFIG_UP).get();
        log.debug("create stream status for scale up stream {}", createStreamStatus);

        createStreamStatus = controller.createStream(CONFIG_DOWN).get();
        log.debug("create stream status for scaledown stream {}", createStreamStatus);

        log.debug("scale down stream starting segments:" + controller.getCurrentSegments(SCOPE, SCALE_DOWN_STREAM_NAME).get().getSegments().size());

        Map<Double, Double> keyRanges = new HashMap<>();
        keyRanges.put(0.0, 0.5);
        keyRanges.put(0.5, 1.0);

        Boolean status = controller.scaleStream(new StreamImpl(SCOPE, SCALE_DOWN_STREAM_NAME),
                Collections.singletonList(0),
                keyRanges,
                EXECUTOR_SERVICE).getFuture().get();
        assertTrue(status);

        createStreamStatus = controller.createStream(CONFIG_TXN).get();
        log.debug("create stream status for txn stream {}", createStreamStatus);
    }