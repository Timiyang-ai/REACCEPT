static int[]
	offsets(final ISeq<? extends FlatTree<? extends Op<A>, ?>> nodes) {
		final int[] offsets = new int[nodes.size()];

		offsets[0] = 1;
		for (int i = 1; i < offsets.length; ++i) {
			offsets[i] += nodes.get(i).getValue().arity();
		}

		return offsets;
	}