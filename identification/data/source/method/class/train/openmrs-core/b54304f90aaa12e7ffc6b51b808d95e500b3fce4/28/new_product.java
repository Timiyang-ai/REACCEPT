public boolean isSuperClass(Type t) {
		if (t instanceof TypeVariable<?>) {
			TypeVariable<?> typeVar = (TypeVariable<?>) t;
			if (typeVar.getBounds() == null || typeVar.getBounds().length == 0) {
				return parametrizedClass.equals(Object.class);
			}
			for (Type typeBound : typeVar.getBounds()) {
				if (isSuperClass(typeBound)) {
					return true;
				}
			}
			return false;
		} else if (t instanceof Class<?>) {
			return isSuperClass((Class<?>) t);
		} else {
			throw new IllegalArgumentException("Don't know how to handle: " + t.getClass());
		}
	}