public <S> RTree<S> create() {
            if (!minChildren.isPresent())
                minChildren = of(maxChildren / 2);
            return new RTree<S>(new Context(minChildren.get(), maxChildren, selector, splitter));
        }