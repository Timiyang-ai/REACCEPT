void reconcile(final HasMetadata involvedObject,
                   final Topic k8sTopic, final Topic kafkaTopic, final Topic privateTopic,
                   final Handler<AsyncResult<Void>> reconciliationResultHandler) {

        {
            TopicName topicName = k8sTopic != null ? k8sTopic.getTopicName() : kafkaTopic != null ? kafkaTopic.getTopicName() : privateTopic != null ? privateTopic.getTopicName() : null;
            logger.info("Reconciling topic {}, k8sTopic:{}, kafkaTopic:{}, privateTopic:{}", topicName, k8sTopic==null?"null":"nonnull", kafkaTopic==null?"null":"nonnull", privateTopic==null?"null":"nonnull");
        }
        if (privateTopic == null) {
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // All three null: This happens reentrantly when a topic or configmap is deleted
                    logger.debug("All three topics null during reconciliation.");
                    reconciliationResultHandler.handle(Future.succeededFuture());
                } else {
                    // it's been created in Kafka => create in k8s and privateState
                    logger.debug("topic created in kafka, will create cm in k8s and topicStore");
                    enqueue(new CreateConfigMap(kafkaTopic, ar -> {
                        // In all cases, create in privateState
                        if (ar.succeeded()) {
                            enqueue(new CreateInTopicStore(kafkaTopic, involvedObject, reconciliationResultHandler));
                        } else {
                            reconciliationResultHandler.handle(ar);
                        }
                    }));
                }
            } else if (kafkaTopic == null) {
                // it's been created in k8s => create in Kafka and privateState
                logger.debug("cm created in k8s, will create topic in kafka and topicStore");
                enqueue(new CreateKafkaTopic(k8sTopic, involvedObject, ar -> {
                    // In all cases, create in privateState
                    if (ar.succeeded()) {
                        enqueue(new CreateInTopicStore(k8sTopic, involvedObject, reconciliationResultHandler));
                    } else {
                        reconciliationResultHandler.handle(ar);
                    }
                }));
            } else {
                update2Way(involvedObject, k8sTopic, kafkaTopic, reconciliationResultHandler);
            }
        } else {
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // delete privateState
                    logger.debug("cm deleted in k8s and topic deleted in kafka => delete from topicStore");
                    enqueue(new DeleteFromTopicStore(privateTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                    reconciliationResultHandler.handle(Future.succeededFuture());
                } else {
                    // it was deleted in k8s so delete in kafka and privateState
                    logger.debug("cm deleted in k8s => delete topic from kafka and from topicStore");
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
                logger.debug("topic deleted in kafkas => delete cm from k8s and from topicStore");
                enqueue(new DeleteConfigMap(k8sTopic.getTopicName(), ar -> {
                    if (ar.succeeded()) {
                        enqueue(new DeleteFromTopicStore(k8sTopic.getTopicName(), involvedObject, reconciliationResultHandler));
                    } else {
                        reconciliationResultHandler.handle(ar);
                    }
                }));
            } else {
                // all three exist
                logger.debug("3 way diff");
                update3Way(involvedObject, k8sTopic, kafkaTopic, privateTopic, reconciliationResultHandler);
            }
        }
    }