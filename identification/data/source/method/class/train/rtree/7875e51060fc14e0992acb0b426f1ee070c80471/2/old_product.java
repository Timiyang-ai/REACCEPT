public <S> RTree<S> create() {
			if (!minChildren.isPresent())
				minChildren = of(maxChildren / 2);
			return new RTree<S>(Optional.<Node<S>> absent(), 0, new Context(
					minChildren.get(), maxChildren, selector, splitter));
		}