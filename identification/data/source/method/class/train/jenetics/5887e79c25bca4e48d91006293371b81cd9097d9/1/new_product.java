static int[]
	offsets(final ISeq<? extends FlatTree<? extends Op<?>, ?>> nodes) {
		final int[] offsets = new int[nodes.size()];

		int offset = 1;
		for (int i = 0; i < offsets.length; ++i) {
			final Op<?> op = nodes.get(i).getValue();

			offsets[i] = op.arity() == 0 ? -1 : offset;
			offset += nodes.get(i).getValue().arity();
		}

		return offsets;
	}