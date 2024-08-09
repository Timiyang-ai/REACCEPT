public void onTopicCreated(TopicName topicName, Handler<AsyncResult<Void>> resultHandler) {
        // XXX currently runs on the ZK thread, requiring a synchronized inFlight
        // is it better to put this check in the topic deleted event?
        if (inFlight.shouldProcessTopicCreate(topicName)) {
            Handler<AsyncResult<TopicMetadata>> handler = new Handler<AsyncResult<TopicMetadata>>() {
                BackOff backOff = new BackOff();

                @Override
                public void handle(AsyncResult<TopicMetadata> metadataResult) {
                    if (metadataResult.failed()) {
                        if (metadataResult.cause() instanceof UnknownTopicOrPartitionException) {
                            // In this case it is most likely that we've been notified by ZK
                            // before Kafka has finished creating the topic, so we retry
                            // with exponential backoff.
                            long delay;
                            try {
                                delay = backOff.delayMs();
                            } catch (MaxAttemptsExceededException e) {
                                resultHandler.handle(Future.failedFuture(e));
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
                            resultHandler.handle(Future.failedFuture(metadataResult.cause()));
                        }
                    } else {
                        // We now have the metadata we need to create the
                        // ConfigMap...
                        Topic topic = TopicSerialization.fromTopicMetadata(metadataResult.result());
                        enqueue(new CreateConfigMap(topic, kubeResult -> {
                            if (kubeResult.succeeded()) {
                                enqueue(new CreateInTopicStore(topic, null, resultHandler));
                            } else {
                                resultHandler.handle(kubeResult);
                            }
                        }));
                    }
                }
            };
            kafka.topicMetadata(topicName, handler);
        } else {
            resultHandler.handle(Future.succeededFuture());
        }
    }