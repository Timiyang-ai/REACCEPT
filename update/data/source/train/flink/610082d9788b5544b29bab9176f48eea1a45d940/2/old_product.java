@Override
	public boolean next(PactRecord target) throws IOException
	{
		// check for the left over element
		if (this.next == null) {
			// we need to make a case distinction whether we are currently reading through full blocks
			// or filling blocks anew
			if (this.bufferCurrentlyRead != null)
			{				
				// we are reading from a full block
				if (this.bufferCurrentlyRead.read(target)) {
					// the current buffer had another element
					return true;
				}
				else {
					// the current buffer is exhausted
					this.consumedBuffers.add(this.bufferCurrentlyRead);
					if (this.fullBuffers.isEmpty()) {
						// no more elements in this block.
						this.bufferCurrentlyRead = null;
						return false;
					}
					else {
						// go to next input block
						this.bufferCurrentlyRead = this.fullBuffers.remove(0);
						if (this.bufferCurrentlyRead.read(target)) {
							return true;
						}
						else {
							throw new IOException("BlockResettableIterator: " +
									"BUG - Could not de-serialize element newly obtaint input block buffer.");
						}
					}
				}
			}
			else if (this.bufferCurrentlyFilled != null) {
				// we are reading from the input reader and filling the block along
				if (this.input.next(target)) {
					if (this.bufferCurrentlyFilled.write(target)) {
						// object fit into current buffer
						return true;
					}
					else {
						// object did not fit into current buffer
						// add the current buffer to the full buffers
						final int fillPosition = this.bufferCurrentlyFilled.getPosition();
						final MemorySegment seg = this.bufferCurrentlyFilled.dispose();
						this.bufferCurrentlyFilled = null;
						
						final Buffer.Input in = new Buffer.Input(seg);
						in.reset(fillPosition);
						this.consumedBuffers.add(in);
						
						// get the next buffer
						if (this.emptySegments.isEmpty()) {
							// no more empty segments. the current element is left over
							target.copyTo(this.leftOverRecord);
							this.leftOver = true;
							return false;
						}
						else {
							// next segment available, use it.
							this.bufferCurrentlyFilled = new Buffer.Output(this.emptySegments.remove(this.emptySegments.size() - 1));
							if (this.bufferCurrentlyFilled.write(target)) {
								// object fit into next buffer
								return true;
							}
							else {
								throw new IOException("BlockResettableIterator: " +
									"Could not serialize element into fresh block buffer - element is too large.");
							}
						}
					}
				}
				else {
					// no more input from the reader
					this.noMoreBlocks = true;
					return false;
				}
			}
			else {
				// we have a repeated call to hasNext() an either the buffers are completely filled, or completely read
				// or the iterator was closed
				if (this.closed) {
					throw new IllegalStateException("Iterator was closed.");
				}
				return false;
			}
		}
		else {
			this.next.copyTo(target);
			this.next = null;
			return true;
		}
	}