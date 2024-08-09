public Builder<G, C> constraint(final Constraint<G, C> constraint) {
			_constraint = requireNonNull(constraint);
			return this;
		}