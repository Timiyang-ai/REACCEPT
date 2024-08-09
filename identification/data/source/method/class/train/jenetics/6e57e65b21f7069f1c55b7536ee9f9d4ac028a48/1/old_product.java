public ISeq<Var<Double>> vars() {
		return ISeq.of(
			_tree.stream()
				.filter(node -> node.getValue() instanceof Var<?>)
				.map(node -> (Var<Double>)node.getValue())
				.collect(Collectors.toCollection(() ->
					new TreeSet<>(Comparator.comparing(Var::name))))
		);
	}