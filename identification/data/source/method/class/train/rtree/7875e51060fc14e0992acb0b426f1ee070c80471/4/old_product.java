public <S> RTree<S> create() {
			if (!minChildren.isPresent())
				minChildren = of((int) Math.round(maxChildren
						* DEFAULT_FILLING_FACTOR));
			return new RTree<S>(new Context(minChildren.get(), maxChildren,
					selector, splitter));
		}