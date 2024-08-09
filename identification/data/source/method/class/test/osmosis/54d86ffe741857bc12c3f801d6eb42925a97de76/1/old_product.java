public void process(Bound bound) {

		// Only add the Bound if the origin string isn't empty
		if (bound.getOrigin() != "") {
			beginOpenElement();
			// Write with the US locale (to force . instead of , as the decimal separator)
			// Use only 5 decimal places (~1.2 meter resolution should be sufficient for Bound)
			addAttribute("box", String.format(
			        Locale.US,
			        "%.5f,%.5f,%.5f,%.5f",
			        bound.getBottom(),
			        bound.getLeft(),
			        bound.getTop(),
			        bound.getRight()));
			addAttribute("origin", bound.getOrigin());
			endOpenElement(true);
		}
	}