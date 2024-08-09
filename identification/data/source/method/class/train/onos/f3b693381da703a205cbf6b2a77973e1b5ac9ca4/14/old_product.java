public static Criterion matchSctpSrc(Short sctpPort) {
        return new SctpPortCriterion(sctpPort, Type.SCTP_SRC);
    }