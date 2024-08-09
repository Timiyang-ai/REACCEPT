public E newFlyweight(long address) {
		try {
			return constructor.newInstance(address);
		} catch (RuntimeException e) {
        	throw e;
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
	}