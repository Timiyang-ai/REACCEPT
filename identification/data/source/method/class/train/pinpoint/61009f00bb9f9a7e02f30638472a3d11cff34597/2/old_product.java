private boolean userToWasFilter(List<SpanBo> transaction) {
        final List<SpanBo> toNode = findToNode(transaction);
        for (SpanBo span : toNode) {
            if (span.isRoot()) {
                if (checkResponseCondition(span.getElapsed(), isError(span))) {
                    return true;
                }

            }
        }
        return false;
    }