public final Type getFresh() {
    	return this.getFreshInstance(new IdentityHashMap<TypeVar.TypeInstance, TypeVar>());
    }