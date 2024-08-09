public void onConfigMapAdded(ConfigMap configMap, Handler<AsyncResult<Void>> resultHandler) {
        if (cmPredicate.test(configMap)) {
            TopicName topicName = new TopicName(configMap);
            if (inFlight.shouldProcessConfigMapAdded(topicName)) {
                Topic topic = TopicSerialization.fromConfigMap(configMap);
                enqueue(new CreateKafkaTopic(topic, configMap, ar -> {
                    if (ar.succeeded()) {
                        enqueue(new CreateInTopicStore(topic, configMap, resultHandler));
                    } else {
                        resultHandler.handle(Future.failedFuture(ar.cause()));
                    }
                }));
            } else {
                resultHandler.handle(Future.succeededFuture());
            }
        } else {
            resultHandler.handle(Future.succeededFuture());
        }
    }