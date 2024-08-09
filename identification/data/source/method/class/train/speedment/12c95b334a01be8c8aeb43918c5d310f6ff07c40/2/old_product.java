@Override
	public Optional<String> transform(Generator gen, Constructor model) {
        requireNonNulls(gen, model);
        
		return Optional.of(
			ifelse(gen.on(model.getJavadoc()), s -> s + nl(), "") +
			gen.onEach(model.getModifiers()).collect(joinIfNotEmpty(" ", "", " ")) +
			renderName(gen, model)
                .orElseThrow(() -> new UnsupportedOperationException(
                    "Could not find a nameable parent of constructor."
                )) +
			gen.onEach(model.getFields()).collect(
				Collectors.joining(
                    fieldSeparator(model), 
                    (model.getFields().size() > 3) ? "(" + nl() : "(", 
                    ")"
                )
			) + " " + 
            gen.onEach(model.getExceptions()).collect(joinIfNotEmpty(", ", "throws ", " ")) +
            block(
				model.getCode().stream().collect(
					Collectors.joining(nl())
				)
			)
		);
	}