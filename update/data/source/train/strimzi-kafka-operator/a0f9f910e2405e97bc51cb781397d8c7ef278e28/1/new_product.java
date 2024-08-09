Future<Void> reconcile(Reconciliation reconciliation, final LogContext logContext, final HasMetadata involvedObject,
                   final Topic k8sTopic, final Topic kafkaTopic, final Topic privateTopic) {
        final Future<Void> reconciliationResultHandler;
        {
            TopicName topicName = k8sTopic != null ? k8sTopic.getTopicName() : kafkaTopic != null ? kafkaTopic.getTopicName() : privateTopic != null ? privateTopic.getTopicName() : null;
            LOGGER.info("{}: Reconciling topic {}, k8sTopic:{}, kafkaTopic:{}, privateTopic:{}", logContext, topicName, k8sTopic == null ? "null" : "nonnull", kafkaTopic == null ? "null" : "nonnull", privateTopic == null ? "null" : "nonnull");
        }
        if (privateTopic == null) {
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // All three null: This happens reentrantly when a topic or KafkaTopic is deleted
                    LOGGER.debug("{}: All three topics null during reconciliation.", logContext);
                    reconciliationResultHandler = Future.succeededFuture();
                } else {
                    // it's been created in Kafka => create in k8s and privateState
                    LOGGER.debug("{}: topic created in kafka, will create KafkaTopic in k8s and topicStore", logContext);
                    reconciliationResultHandler = createResource(logContext, kafkaTopic)
                            .compose(createdKt -> {
                                reconciliation.observedTopicFuture(createdKt);
                                return createInTopicStore(logContext, kafkaTopic, involvedObject);
                            });
                }
            } else if (kafkaTopic == null) {
                // it's been created in k8s => create in Kafka and privateState
                LOGGER.debug("{}: KafkaTopic created in k8s, will create topic in kafka and topicStore", logContext);
                reconciliationResultHandler = createKafkaTopic(logContext, k8sTopic, involvedObject)
                    .compose(ignore -> createInTopicStore(logContext, k8sTopic, involvedObject))
                    // Kafka will set the message.format.version, so we need to update the KafkaTopic to reflect
                    // that to avoid triggering another reconciliation
                    .compose(ignored -> getFromKafka(k8sTopic.getTopicName()))
                    .compose(kafkaTopic2 -> {
                        LOGGER.debug("Post-create kafka {}", kafkaTopic2);
                        return update3Way(reconciliation, logContext, involvedObject, k8sTopic, kafkaTopic2, k8sTopic);
                    });
                    //.compose(createdKafkaTopic -> update3Way(logContext, involvedObject, k8sTopic, createdKafkaTopic, k8sTopic));
            } else {
                reconciliationResultHandler = update2Way(reconciliation, logContext, involvedObject, k8sTopic, kafkaTopic);
            }
        } else {
            if (k8sTopic == null) {
                if (kafkaTopic == null) {
                    // delete privateState
                    LOGGER.debug("{}: KafkaTopic deleted in k8s and topic deleted in kafka => delete from topicStore", logContext);
                    reconciliationResultHandler = deleteFromTopicStore(logContext, involvedObject, privateTopic.getTopicName());
                } else {
                    // it was deleted in k8s so delete in kafka and privateState
                    LOGGER.debug("{}: KafkaTopic deleted in k8s => delete topic from kafka and from topicStore", logContext);
                    reconciliationResultHandler = deleteKafkaTopic(logContext, kafkaTopic.getTopicName())
                        .compose(ignored -> deleteFromTopicStore(logContext, involvedObject, privateTopic.getTopicName()));
                }
            } else if (kafkaTopic == null) {
                // it was deleted in kafka so delete in k8s and privateState
                LOGGER.debug("{}: topic deleted in kafkas => delete KafkaTopic from k8s and from topicStore", logContext);
                reconciliationResultHandler = deleteResource(logContext, privateTopic.getOrAsKubeName())
                        .compose(ignore -> deleteFromTopicStore(logContext, involvedObject, privateTopic.getTopicName()));
            } else {
                // all three exist
                LOGGER.debug("{}: 3 way diff", logContext);
                reconciliationResultHandler = update3Way(reconciliation, logContext, involvedObject,
                        k8sTopic, kafkaTopic, privateTopic);
            }
        }
        return reconciliationResultHandler;
    }