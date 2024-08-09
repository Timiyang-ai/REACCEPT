static
	public Object decode(Object object){

		if(object instanceof Computable){
			Computable computable = (Computable)object;

			return computable.getResult();
		} // End if

		if(object instanceof Collection){
			Collection<?> rawValues = (Collection<?>)object;

			Collection<Object> decodedValues;

			// Try to preserve the original contract
			if(rawValues instanceof Set){
				decodedValues = new LinkedHashSet<>(rawValues.size());
			} else

			{
				decodedValues = new ArrayList<>(rawValues.size());
			}

			for(Object rawValue : rawValues){
				decodedValues.add(decode(rawValue));
			}

			return decodedValues;
		}

		return object;
	}