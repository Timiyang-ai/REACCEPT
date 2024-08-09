    @Test
    public void addOrder_shouldSetTheOrderGroupOnTheAddedOrder() {
        
        OrderGroup orderGroup = new OrderGroup();

        Order firstOrder = new Order();
        Order secondOrder = new Order();

        orderGroup.addOrder(firstOrder);
        orderGroup.addOrder(secondOrder);

        List<Order> orders = orderGroup.getOrders();

        Assert.assertNotNull("should have orderGroup in order", orders.get(0).getOrderGroup());
        Assert.assertNotNull("should have orderGroup in order", orders.get(1).getOrderGroup());
    }