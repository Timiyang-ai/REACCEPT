void onConfigMapAdded(ConfigMap configMap, Handler<AsyncResult<Void>> resultHandler) {
        if (cmPredicate.test(configMap)) {
            final Topic k8sTopic;
            try {
                k8sTopic = TopicSerialization.fromConfigMap(configMap);
            } catch (InvalidConfigMapException e) {
                resultHandler.handle(Future.failedFuture(e));
                return;
            }
            Handler<Future<Void>> action = new Reconciliation("onConfigMapAdded") {
                @Override
                public void handle(Future<Void> fut) {
                    Controller.this.reconcileOnCmChange(configMap, k8sTopic, fut);
                }
            };
            inFlight.enqueue(new TopicName(configMap), resultHandler, action);
        } else {
            resultHandler.handle(Future.succeededFuture());
        }
    }