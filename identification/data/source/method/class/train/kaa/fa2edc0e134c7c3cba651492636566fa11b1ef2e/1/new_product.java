public void recalculate() {
        LOG.info("DynamicLoadManager recalculate() started... lastBootstrapServersUpdateFailed {}", lastBootstrapServersUpdateFailed);
        if (lastBootstrapServersUpdateFailed) {
            LOG.trace("Registred {} Bootstrap servers", bootstrapsMap.size());
            lastBootstrapServersUpdateFailed = false;
            for (BootstrapNodeInfo bootstrapNodeInfo : bootstrapsMap.values()) {
                updateBootstrap(bootstrapNodeInfo);
            }
        }
        if (dynamicMgmt != null) {
            Map<Integer, OperationsServerLoadHistory> opsServerHistory = new HashMap<Integer, OperationsServerLoadHistory>();
            for (Integer accessPointId : opsServersMap.keySet()) {
                opsServerHistory.put(accessPointId, opsServersMap.get(accessPointId).history);
            }
            Map<Integer, RedirectionRule> rules = dynamicMgmt.recalculate(opsServerHistory);
            LOG.trace("DynamicLoadManager recalculate() got {} redirection rules", rules.size());
            for (Integer accessPointId : rules.keySet()) {
                if (opsServersMap.containsKey(accessPointId)) {
                    sendRedirectionRule(accessPointId, opsServersMap.get(accessPointId).nodeInfo, rules.get(accessPointId));
                } else {
                    LOG.error("Operations server {} redirection rule exist, but NO server available, skip setting rule.", accessPointId);
                }
            }
        }
    }