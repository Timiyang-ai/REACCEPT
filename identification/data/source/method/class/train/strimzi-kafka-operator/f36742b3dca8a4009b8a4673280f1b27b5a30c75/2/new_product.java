void reconcile(final LogContext logContext, final HasMetadata involvedObject,
                   final Topic k8sTopic, final Topic kafkaTopic, final Topic privateTopic,
                   final Handler<AsyncResult<Void>> reconciliationResultHandler) {

        {
            TopicName topicName = k8sTopic != null ? k8sTopic.getTopicName() : kafkaTopic != null ? kafkaTopic.getTopicName() : privateTopic != null ? privateTopic.getTopicName() : null;
            LOGGER.info("{}: Reconciling topic {}, k8sTopic:{}, kafkaTopic:{}, privateTopic:{}", logContext, topicName, k8sTopic == null ? "null" : "nonnull", kafkaTopic == null ? "null" : "nonnull", privateTopic == null ? "null" : "nonnull");
        }
        if (privateTopic == null) {
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // All three null: This happens reentrantly when a topic or KafkaTopic is deleted
                    LOGGER.debug("{}: All three topics null during reconciliation.", logContext);
                    reconciliationResultHandler.handle(Future.succeededFuture());
                } else {
                    // it's been created in Kafka => create in k8s and privateState
                    LOGGER.debug("{}: topic created in kafka, will create KafkaTopic in k8s and topicStore", logContext);
                    enqueue(new CreateResource(logContext, kafkaTopic, ar -> {
                        // In all cases, create in privateState
                        if (ar.succeeded()) {
                            enqueue(new CreateInTopicStore(logContext, kafkaTopic, involvedObject, reconciliationResultHandler));
                        } else {
                            reconciliationResultHandler.handle(ar);
                        }
                    }));
                }
            } else if (kafkaTopic == null) {
                // it's been created in k8s => create in Kafka and privateState
                LOGGER.debug("{}: KafkaTopic created in k8s, will create topic in kafka and topicStore", logContext);
                enqueue(new CreateKafkaTopic(logContext, k8sTopic, involvedObject, ar -> {
                    // In all cases, create in privateState
                    if (ar.succeeded()) {
                        enqueue(new CreateInTopicStore(logContext, k8sTopic, involvedObject, reconciliationResultHandler));
                    } else {
                        reconciliationResultHandler.handle(ar);
                    }
                }));
            } else {
                update2Way(logContext, involvedObject, k8sTopic, kafkaTopic, reconciliationResultHandler);
            }
        } else {
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // delete privateState
                    LOGGER.debug("{}: KafkaTopic deleted in k8s and topic deleted in kafka => delete from topicStore", logContext);
                    enqueue(new DeleteFromTopicStore(logContext, privateTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                } else {
                    // it was deleted in k8s so delete in kafka and privateState
                    LOGGER.debug("{}: KafkaTopic deleted in k8s => delete topic from kafka and from topicStore", logContext);
                    enqueue(new DeleteKafkaTopic(logContext, kafkaTopic.getTopicName(), ar -> {
                        if (ar.succeeded()) {
                            enqueue(new DeleteFromTopicStore(logContext, privateTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                        } else {
                            reconciliationResultHandler.handle(ar);
                        }
                    }));

                }
            } else if (kafkaTopic == null) {
                // it was deleted in kafka so delete in k8s and privateState
                LOGGER.debug("{}: topic deleted in kafkas => delete KafkaTopic from k8s and from topicStore", logContext);
                enqueue(new DeleteResource(logContext, privateTopic.getOrAsKubeName(), ar -> {
                    if (ar.succeeded()) {
                        enqueue(new DeleteFromTopicStore(logContext, privateTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                    } else {
                        reconciliationResultHandler.handle(ar);
                    }
                }));
            } else {
                // all three exist
                LOGGER.debug("{}: 3 way diff", logContext);
                update3Way(logContext, involvedObject, k8sTopic, kafkaTopic, privateTopic, reconciliationResultHandler);
            }
        }
    }