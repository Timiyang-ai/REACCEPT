@Override
	public Optional<String> transform(Generator gen, Constructor model) {
        requireNonNull(gen);
        requireNonNull(model);
        
		return Optional.of(
			ifelse(gen.on(model.getJavadoc()), s -> s + nl(), EMPTY) +
			gen.onEach(model.getModifiers()).collect(joinIfNotEmpty(SPACE, EMPTY, SPACE)) +
			renderName(gen, model)
                .orElseThrow(() -> new UnsupportedOperationException(
                    "Could not find a nameable parent of constructor."
                )) +
			gen.onEach(model.getFields()).collect(
				Collectors.joining(COMMA_SPACE, PS, PE)
			) + SPACE + 
            gen.onEach(model.getExceptions()).collect(joinIfNotEmpty(COMMA_SPACE, THROWS, SPACE)) +
            block(
				model.getCode().stream().collect(
					Collectors.joining(nl())
				)
			)
		);
	}