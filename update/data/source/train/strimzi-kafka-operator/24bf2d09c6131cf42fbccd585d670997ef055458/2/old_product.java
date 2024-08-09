void reconcile(HasMetadata involvedObject,
                   Topic k8sTopic, Topic kafkaTopic, Topic privateTopic, Handler<AsyncResult<Void>> reconciliationResultHandler) {
        if (privateTopic == null) {
            class CreateInTopicStoreHandler implements Handler<AsyncResult<Void>>  {

                private final Topic source;

                CreateInTopicStoreHandler(Topic source) {
                    this.source = source;
                }

                @Override
                public void handle(AsyncResult<Void> ar) {
                    // In all cases, create in privateState
                    if (ar.succeeded()) {
                        enqueue(new CreateInTopicStore(source, involvedObject, reconciliationResultHandler));
                    } else {
                        reconciliationResultHandler.handle(ar);
                    }
                }
            }
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // All three null? This shouldn't be possible
                    logger.error("All three topics null during reconciliation. This should be impossible.");
                    return;
                } else {
                    // it's been created in Kafka => create in k8s and privateState
                    enqueue(new CreateConfigMap(kafkaTopic, new CreateInTopicStoreHandler(kafkaTopic)));

                }
            } else if (kafkaTopic == null) {
                // it's been created in k8s => create in Kafka and privateState
                enqueue(new CreateKafkaTopic(k8sTopic, involvedObject, new CreateInTopicStoreHandler(k8sTopic)));
            } else if (TopicDiff.diff(kafkaTopic, k8sTopic).isEmpty()) {
                // they're the same => do nothing
                logger.debug("k8s and kafka versions of topic '{}' are the same", kafkaTopic.getTopicName());
                enqueue(new CreateInTopicStore(kafkaTopic, involvedObject, reconciliationResultHandler));
            } else {
                // TODO use whichever has the most recent mtime
                throw new RuntimeException("Not implemented");
            }
        } else {
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // delete privateState
                    enqueue(new DeleteFromTopicStore(privateTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                } else {
                    // it was deleted in k8s so delete in kafka and privateState
                    enqueue(new DeleteKafkaTopic(kafkaTopic.getTopicName(), involvedObject, ar -> {
                        if (ar.succeeded()) {
                            enqueue(new DeleteFromTopicStore(kafkaTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                        } else {
                            reconciliationResultHandler.handle(ar);
                        }
                    }));

                }
            } else if (kafkaTopic == null) {
                // it was deleted in kafka so delete in k8s and privateState
                enqueue(new DeleteConfigMap(k8sTopic.getTopicName(), ar -> {
                    if (ar.succeeded()) {
                        enqueue(new DeleteFromTopicStore(k8sTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                    } else {
                        reconciliationResultHandler.handle(ar);
                    }
                }));
            } else {
                // all three exist
                update3Way(involvedObject, k8sTopic, kafkaTopic, privateTopic, reconciliationResultHandler);
            }
        }
    }