public String toStringWithOutNature(String split) {
		
		StringBuilder sb = new StringBuilder(terms.get(0).getRealName()) ;
		
		for (int i = 1; i < terms.size(); i++) {
			sb.append(split);
			sb.append(terms.get(i).getRealName()) ;
		}
		
		return sb.toString();
	}