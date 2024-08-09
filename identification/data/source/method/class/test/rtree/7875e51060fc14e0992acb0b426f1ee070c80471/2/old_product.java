public <S> RTree<S> create() {
			if (minChildren == null)
				minChildren = maxChildren / 2;
			return new RTree<S>(Optional.<Node<S>> absent(), new Context(
					minChildren, maxChildren, selector, splitter));
		}