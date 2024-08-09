public default MSeq<T> asMSeq() {
		return this instanceof MSeq<?> ? (MSeq<T>)this : MSeq.of(this);
	}