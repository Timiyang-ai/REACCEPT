private boolean wasToBackendFilter(List<SpanBo> transaction) {
        final List<SpanBo> fromNode = findFromNode(transaction);
        for (SpanBo span : fromNode) {
            final List<SpanEventBo> eventBoList = span.getSpanEventBoList();
            if (eventBoList == null) {
                continue;
            }
            for (SpanEventBo event : eventBoList) {
                final ServiceType eventServiceType = serviceTypeRegistryService.findServiceType(event.getServiceType());
                if (isToNode(event.getDestinationId(), eventServiceType)) {
                    if (checkResponseCondition(event.getEndElapsed(), event.hasException())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }