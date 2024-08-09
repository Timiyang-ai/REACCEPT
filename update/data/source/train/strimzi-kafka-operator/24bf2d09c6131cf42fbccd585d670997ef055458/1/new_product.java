void onTopicCreated(TopicName topicName, Handler<AsyncResult<Void>> resultHandler) {
        // XXX currently runs on the ZK thread, requiring a synchronized inFlight
        // is it better to put this check in the topic deleted event?
        Handler<Future<Void>> futureHandler = new Reconciliation("onTopicCreated") {
            @Override
            public void handle(Future<Void> fut) {
                Handler<AsyncResult<TopicMetadata>> handler = new Handler<AsyncResult<TopicMetadata>>() {
                    private final BackOff backOff = new BackOff();

                    @Override
                    public void handle(AsyncResult<TopicMetadata> metadataResult) {
                        if (metadataResult.succeeded()) {
                            if (metadataResult.result() == null) {
                                // In this case it is most likely that we've been notified by ZK
                                // before Kafka has finished creating the topic, so we retry
                                // with exponential backoff.
                                long delay;
                                try {
                                    delay = backOff.delayMs();
                                    logger.debug("Topic {} created in ZK, but no metadata available from Kafka yet: Backing off for {}ms", topicName, delay);
                                } catch (MaxAttemptsExceededException e) {
                                    logger.info("Topic {} created in ZK, and no metadata available from Kafka after {}ms, giving up for now", topicName, backOff.totalDelayMs());
                                    fut.fail(e);
                                    return;
                                }

                                if (delay < 1) {
                                    // vertx won't tolerate a zero delay
                                    vertx.runOnContext(timerId -> kafka.topicMetadata(topicName, this));
                                } else {
                                    vertx.setTimer(TimeUnit.MILLISECONDS.convert(delay, TimeUnit.MILLISECONDS),
                                            timerId -> kafka.topicMetadata(topicName, this));
                                }
                            } else {
                                // We now have the metadata we need to create the
                                // ConfigMap...
                                Topic kafkaTopic = TopicSerialization.fromTopicMetadata(metadataResult.result());
                                reconcileOnTopicChange(topicName, kafkaTopic, fut);
                            }
                        } else {
                            fut.handle(metadataResult.map((Void) null));
                        }
                    }
                };
                kafka.topicMetadata(topicName, handler);
            }
        };
        inFlight.enqueue(topicName, resultHandler, futureHandler);
    }