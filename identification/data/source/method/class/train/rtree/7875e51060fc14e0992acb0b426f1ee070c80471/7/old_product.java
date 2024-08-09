public <S> RTree<S> create() {
            if (!maxChildren.isPresent())
                if (star)
                    maxChildren = of(MAX_CHILDREN_DEFAULT_STAR);
                else
                    maxChildren = of(MAX_CHILDREN_DEFAULT_GUTTMAN);
            if (!minChildren.isPresent())
                minChildren = of((int) Math.round(maxChildren.get() * DEFAULT_FILLING_FACTOR));
            return new RTree<S>(new Context(minChildren.get(), maxChildren.get(), selector,
                    splitter));
        }