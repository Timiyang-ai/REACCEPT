private boolean wasToBackendFilter(List<SpanBo> transaction) {
        for (SpanBo span : transaction) {
            if (isFromNode(span.getApplicationId(), span.getServiceType())) {
                List<SpanEventBo> eventBoList = span.getSpanEventBoList();
                if (eventBoList == null) {
                    continue;
                }
                for (SpanEventBo event : eventBoList) {
                    if (isToNode(event.getDestinationId(), event.getServiceType())) {
                        return checkResponseCondition(event.getEndElapsed(), event.hasException())
                                && filterAgentName(span.getAgentId(), null);
                    }
                }
            }
        }
        return false;
    }