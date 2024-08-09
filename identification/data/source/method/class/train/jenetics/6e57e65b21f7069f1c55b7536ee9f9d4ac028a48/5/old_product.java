public ISeq<Var<Double>> vars() {
		return _tree.stream()
			.filter(node -> node.getValue() instanceof Var)
			.map(node -> (Var<Double>)node.getValue())
			.sorted(Comparator.comparing(Var::name))
			.collect(ISeq.toISeq());
	}