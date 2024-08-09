@Override
	public Optional<String> transform(Generator cg, Constructor model) {
		return Optional.of(
			ifelse(cg.on(model.getJavadoc()), s -> s + nl(), EMPTY) +
			cg.onEach(model.getModifiers()).collect(CodeCombiner.joinIfNotEmpty(SPACE, EMPTY, SPACE)) +
			renderName(cg, model)
                .orElseThrow(() -> new UnsupportedOperationException("Could not find a nameable parent of constructor.")) +
			cg.onEach(model.getFields()).collect(
				Collectors.joining(COMMA_SPACE, PS, PE)
			) + SPACE + 
            cg.onEach(model.getExceptions()).collect(CodeCombiner.joinIfNotEmpty(COMMA_SPACE, THROWS, SPACE)) +
            block(
				model.getCode().stream().collect(
					Collectors.joining(nl())
				)
			)
		);
	}