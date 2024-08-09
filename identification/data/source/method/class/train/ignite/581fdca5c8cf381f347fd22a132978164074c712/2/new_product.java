public <I extends Serializable, O extends Serializable> InfModel<I, Future<O>> build(InfModelReader reader,
        InfModelParser<I, O, ?> parser);