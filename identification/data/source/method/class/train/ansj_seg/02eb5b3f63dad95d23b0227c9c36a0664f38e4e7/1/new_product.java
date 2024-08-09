public String toStringWithOutNature(String split) {
		
		if(terms==null || terms.size()==0){
			return "" ;
		}
		
		Iterator<Term> iterator = terms.iterator() ;
		
		StringBuilder sb = new StringBuilder(iterator.next().getRealName()) ;
		
		while(iterator.hasNext()){
			sb.append(split);
			sb.append(iterator.next().getRealName()) ;
		}
		
		return sb.toString();
	}