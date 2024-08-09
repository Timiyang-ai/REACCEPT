@Override
		public boolean next(PactString target) {
			final char[] data = this.toTokenize.getCharArray();
			final int limit = this.limit;
			int pos = this.pos;
			
			// skip the delimiter
			for (; pos < limit && Character.isWhitespace(data[pos]); pos++);
			
			if (pos >= limit) {
				this.pos = pos;
				return false;
			}
			
			final int start = pos;
			for (; pos < limit && !Character.isWhitespace(data[pos]); pos++);
			this.pos = pos;
			target.setValue(this.toTokenize, start, pos - start);
			return true;
		}