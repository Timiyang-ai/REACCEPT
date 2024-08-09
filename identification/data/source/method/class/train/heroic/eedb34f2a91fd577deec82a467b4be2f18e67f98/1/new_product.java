public void throwUnlessEmpty(final String name) {
        if (args.isEmpty() && kw.isEmpty()) {
            return;
        }

        final List<String> parts = new ArrayList<>();
        final Joiner on = Joiner.on(" and ");

        if (!args.isEmpty()) {
            parts.add(
                args.size() == 1 ? "argument " + args.iterator().next() : "arguments " + args);
        }

        if (!kw.isEmpty()) {
            parts.add((kw.size() == 1 ? "keyword " + kw.keySet().iterator().next()
                : "keywords " + kw.keySet()));
        }

        throw new IllegalStateException(name + ": has trailing " + on.join(parts));
    }