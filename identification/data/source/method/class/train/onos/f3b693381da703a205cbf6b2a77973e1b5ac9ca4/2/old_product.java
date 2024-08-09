public static Criterion matchOpticalSignalType(Short sigType) {
        return new OpticalSignalTypeCriterion(sigType, Type.OCH_SIGTYPE);
    }