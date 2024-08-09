@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Result))
			return false;
		Result r = (Result) obj;
		
		if (EmptyResult.class.isAssignableFrom(r.getClass()) && this.isEmpty()) {
			return true;
		}
		
		if (EmptyResult.class.isAssignableFrom(this.getClass()) && r.isEmpty()) {
			return true;
		}
		
		if (isSingleResult() && r.isSingleResult()) {
			
			if (datatype == null) {
				return false;
			}
			// both are single results
			switch (datatype) {
				case BOOLEAN:
					return (valueBoolean.equals(r.valueBoolean));
				case CODED:
					return (valueCoded.equals(r.valueCoded));
				case DATETIME:
					return (valueDatetime.equals(r.valueDatetime));
				case NUMERIC:
					return (valueNumeric.equals(r.valueNumeric));
				case TEXT:
					return (valueText.equals(r.valueText));
				default:
					return false;
			}
		}
		if (isSingleResult() || r.isSingleResult())
			// we already know they're not both single results, so if one is
			// single, it's not a match
			return false;
		if (this.size() != r.size())
			return false;
		// at this point, we have two results that are lists, so members must
		// match exactly
		for (int i = 0; i < this.size(); i++) {
			if (!this.get(i).equals(r.get(i)))
				return false;
		}
		return true;
	}