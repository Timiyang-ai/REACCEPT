public boolean pushFront (T object) {
		T[] values = this.values;

		if (size == values.length) {
			if (!resizable) {
				return false;
			} else {
				resize(values.length << 1);// *2
				values = this.values;
			}
		}

		int head = this.head;
		head--;
		if (head == -1) {
			head = values.length - 1;
		}
		values[head] = object;

		this.head = head;
		this.size++;
		return true;
	}