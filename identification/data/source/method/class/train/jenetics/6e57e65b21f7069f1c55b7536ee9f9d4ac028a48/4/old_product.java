public default ISeq<T> asISeq() {
		return this instanceof ISeq<?> ? (ISeq<T>)this : ISeq.of(this);
	}