public <KafkaK, KafkaV, ClientK, ClientV>
  Future readTopic(final String group, final String instance, final String topic,
                   Class<? extends ConsumerState<KafkaK, KafkaV, ClientK, ClientV>> consumerStateType,
                   final long maxBytes, final ReadCallback callback) {
    final ConsumerState state;
    try {
      state = getConsumerInstance(group, instance);
    } catch (RestNotFoundException e) {
      callback.onCompletion(null, e);
      return null;
    }

    if (!consumerStateType.isInstance(state)) {
      callback.onCompletion(null, Errors.consumerFormatMismatch());
      return null;
    }

    // Consumer will try reading even if it doesn't exist, so we need to check this explicitly.
    if (!mdObserver.topicExists(topic)) {
      callback.onCompletion(null, Errors.topicNotFoundException());
      return null;
    }

    int workerId = nextWorker.getAndIncrement() % workers.size();
    ConsumerWorker worker = workers.get(workerId);
    return worker.readTopic(
        state, topic, maxBytes,
        new ConsumerWorkerReadCallback<ClientK, ClientV>() {
          @Override
          public void onCompletion(List<? extends ConsumerRecord<ClientK, ClientV>> records) {
            updateExpiration(state);
            if (records == null) {
              callback.onCompletion(null, Errors.consumerInstanceNotFoundException());
            } else {
              callback.onCompletion(records, null);
            }
          }
        }
    );
  }