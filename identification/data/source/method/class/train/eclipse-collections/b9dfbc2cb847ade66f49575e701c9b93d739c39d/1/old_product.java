public T set(int index, T element)
    {
        if (index == 0)
        {
            T previousElement = this.element1;
            this.element1 = element;
            return previousElement;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
    }