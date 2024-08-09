@Override
	public Double weighConceptWord(ConceptWord word) {
		Double weight = 0.0;
		String conceptName = word.getConceptName().getName().toUpperCase();
		String wordString = word.getWord();
		//why is this the case, this seems like invalid data
		if (conceptName.indexOf(wordString) < 0)
			return weight;
		
		//by default every word must at least weigh 1+
		weight = 1.0;
		//TODO make the numbers 5.0, 3.0, 1.0 etc constants
		//Index terms rank highly since they were added for searching
		
		//This is the actual match
		if (conceptName.equals(wordString)) {
			double weightCoefficient = 5.0;
			weight += weightCoefficient;
			//the shorter the word, the higher the increment and the coefficient since it a closer
			//match based on number of characters e.g 'OWN' should weigh more than 'HOME'
			weightCoefficient += (weightCoefficient / wordString.length());
			weight += (weightCoefficient / wordString.length());
			//compute bonus based on the concept name type
			weight += computeBonusWeight(weightCoefficient, word);
		} else if (conceptName.startsWith(wordString)) {
			double weightCoefficient = 3.0;
			
			//the shorter the word, the higher the increment since it a closer match to the name
			// e.g MY in 'MY DEPOT' should weigh more than HOME in 'HOME DEPOT'
			weight += (weightCoefficient / wordString.length());
			weight += computeBonusWeight(weightCoefficient, word);
		} else {
			double weightCoefficient = 1.0;
			
			//still a shorter word should weigh more depending on its index in the full concept name
			//e.g MY in 'IN MY HOME' should weigh more than 'MY' in 'FOR MY HOME', we add 1 so that
			// if 'conceptName.indexOf(wordString)' returns 1, we still divide 5 by something greater than 1
			//e.g 'MARRIAGE' in 'PRE MARRIAGE' should weigh more than 'MARRIAGE' in 'NOT PRE MARRIAGE'
			//and still weigh more then 'MARRIAGE' in 'PRE MARRIAGE RELATIONSHIP'
			weight += ((weightCoefficient / (conceptName.indexOf(wordString) + 1)) * ((conceptName.length() - wordString
			        .length()) / new Double(conceptName.length())));
			weight += computeBonusWeight(weightCoefficient, word);
		}
		
		//round off to 3 decimal places
		return Double.parseDouble(new DecimalFormat("0.000").format(weight));
	}