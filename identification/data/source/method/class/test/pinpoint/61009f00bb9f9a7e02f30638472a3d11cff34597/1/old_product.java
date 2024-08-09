private boolean userToWasFilter(List<SpanBo> transaction) {
        for (SpanBo span : transaction) {
            if (span.isRoot() && includeServiceType(toServiceDescList, span.getServiceType()) && toApplicationName.equals(span.getApplicationId())) {
                return checkResponseCondition(span.getElapsed(), span.getErrCode() > 0)
                        && filterAgentName(null, span.getAgentId());
            }
        }
        return false;
    }