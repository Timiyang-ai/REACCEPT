public <S> RTree<S> create() {
            if (!minChildren.isPresent())
                minChildren = of(maxChildren / 2);
            return new RTree<S>(Optional.<Node<S>> absent(), new Context(minChildren.get(),
                    maxChildren, selector, splitter));
        }