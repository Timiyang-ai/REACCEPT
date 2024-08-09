public <S> RTree<S> create() {
			return new RTree<S>(maxChildren, splitter);
		}