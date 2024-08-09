public boolean include(List<SpanBo> transaction) {
            /*
             * WAS -> WAS
             * if destination is a "WAS", the span of src and dest may exist. need to check if be circular or not.
             * find src first. span (from, to) may exist more than one. so (spanId == parentSpanID) should be checked.
             */

            final List<SpanBo> fromSpanList = findFromNode(transaction);
            if (fromSpanList.isEmpty()) {
                // from span not found
                return false;
            }
            final List<SpanBo> toSpanList = findToNode(transaction);
            if (!toSpanList.isEmpty()) {

                // from -> to compare SpanId & pSpanId filter
                final boolean exactMatch = wasToWasExactMatch(fromSpanList, toSpanList);
                if (exactMatch) {
                    return true;
                }
            }
            if (isToAgentFilter()) {
                // fast skip. toAgent filtering condition exist.
                // url filter not available.
                return false;
            }

            // Check for url pattern should now be done on the caller side (from spans) as to spans are missing at this point
            if (!rpcUrlFilter.accept(fromSpanList)) {
                return false;
            }

            // if agent filter is FromAgentFilter or AcceptAgentFilter(agent filter is not selected), url filtering is available.
            return fromBaseFilter(fromSpanList);
        }