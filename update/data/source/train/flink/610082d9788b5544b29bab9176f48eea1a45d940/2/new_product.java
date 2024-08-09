@Override
	public boolean next(T target) throws IOException
	{
		// check for the left over element
		if (this.readPhase) {
			return getNextRecord(target);
		} else {
			// writing phase. check for leftover first
			if (this.leftOverReturned) {
				// get next record
				if (this.input.next(target)) {
					if (writeNextRecord(target)) {
						return true;
					} else {
						// did not fit into memory, keep as leftover
						this.serializer.copyTo(target, this.leftOverRecord);
						this.leftOverReturned = false;
						return false;
					}
				} else {
					this.noMoreBlocks = true;
					return false;
				}
			} else {
				this.leftOverReturned = true;
				this.serializer.copyTo(this.leftOverRecord, target);
				return true;
			}
		}
	}