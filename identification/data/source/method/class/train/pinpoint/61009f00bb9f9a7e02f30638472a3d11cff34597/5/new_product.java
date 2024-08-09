private boolean filter(List<SpanEventBo> eventBoList) {
            if (CollectionUtils.isEmpty(eventBoList)) {
                return false;
            }
            for (SpanEventBo event : eventBoList) {
                final ServiceType eventServiceType = serviceTypeRegistryService.findServiceType(event.getServiceType());
                if (isToNode(event.getDestinationId(), eventServiceType)) {
                    if (checkResponseCondition(event.getEndElapsed(), event.hasException())) {
                        return true;
                    }
                }
            }
            return false;
        }