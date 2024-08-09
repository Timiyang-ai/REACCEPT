public static Criterion matchIPProtocol(Byte proto) {
        return new IPProtocolCriterion(proto);
    }