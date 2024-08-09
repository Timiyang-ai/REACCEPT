public boolean include(List<SpanBo> transaction) {
            final List<SpanBo> toNode = findToNode(transaction);
            if (logger.isDebugEnabled()) {
                logger.debug("matching toNode spans: {}", toNode);
            }
            for (SpanBo span : toNode) {
                if (fromApplicationName.equals(span.getAcceptorHost())) {
                    if (checkResponseCondition(span.getElapsed(), isError(span))) {
                        return true;
                    }
                }
            }
            return false;
        }