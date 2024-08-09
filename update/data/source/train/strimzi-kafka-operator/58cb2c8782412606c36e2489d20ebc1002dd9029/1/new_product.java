Future<Void> onTopicCreated(LogContext logContext, TopicName topicName) {
        // XXX currently runs on the ZK thread, requiring a synchronized inFlight
        // is it better to put this check in the topic deleted event?
        Reconciliation action = new Reconciliation("onTopicCreated") {
            @Override
            public void handle(Future<Void> fut) {

                TopicMetadataHandler handler = new TopicMetadataHandler(vertx, kafka, topicName, topicMetadataBackOff()) {

                    @Override
                    public void handle(AsyncResult<TopicMetadata> metadataResult) {

                        if (metadataResult.succeeded()) {
                            if (metadataResult.result() == null) {
                                // In this case it is most likely that we've been notified by ZK
                                // before Kafka has finished creating the topic, so we retry
                                // with exponential backoff.
                                retry();
                            } else {
                                // We now have the metadata we need to create the
                                // resource...
                                Topic kafkaTopic = TopicSerialization.fromTopicMetadata(metadataResult.result());
                                reconcileOnTopicChange(logContext, topicName, kafkaTopic, fut);
                            }
                        } else {
                            fut.handle(metadataResult.map((Void) null));
                        }
                    }

                    @Override
                    public void onMaxAttemptsExceeded(MaxAttemptsExceededException e) {
                        fut.fail(e);
                    }
                };
                kafka.topicMetadata(topicName, handler);
            }
        };
        return executeWithTopicLockHeld(logContext, topicName, action);
    }