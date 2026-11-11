package Lab09;

/**
 * AbstractClass của mẫu Template Method.
 * processOrder() là TEMPLATE METHOD (final): cố định thứ tự các bước.
 *  - Bước chung   : validateOrder, updateInventory, printReceipt (đã cài đặt sẵn).
 *  - Bước thay đổi: calculateShippingFee, processPayment (abstract, lớp con bắt buộc cài).
 *  - Hook         : applyDiscount, notifyCustomer (có mặc định, lớp con có thể ghi đè).
 */
public abstract class OrderProcessor {

    // ===== TEMPLATE METHOD =====
    public final void processOrder(Order order) {
        System.out.println("=== Xử lý đơn " + order.getId() + " [" + getPaymentMethodName() + "] ===");
        try {
            validateOrder(order);                        // 1. chung
            double total = sumItems(order);              // 2. chung
            total -= applyDiscount(order, total);        // 3. hook
            total += calculateShippingFee(order, total); // 4. thay đổi
            order.setTotal(total);
            processPayment(order);                       // 5. thay đổi
            updateInventory(order);                      // 6. chung
            notifyCustomer(order);                       // 7. hook
            printReceipt(order);                         // 8. chung
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("[LỖI] Đơn " + order.getId() + " thất bại: " + e.getMessage());
        }
        System.out.println();
    }

    // ===== Các bước chung =====
    protected void validateOrder(Order order) {
        if (order.getItems().isEmpty())
            throw new IllegalArgumentException("Đơn hàng không có sản phẩm");
        for (OrderItem i : order.getItems())
            if (i.getQuantity() <= 0)
                throw new IllegalArgumentException("Số lượng không hợp lệ: " + i.getName());
        System.out.println("[1] Kiểm tra đơn hàng: hợp lệ");
    }

    private double sumItems(Order order) {
        double s = 0;
        for (OrderItem i : order.getItems()) s += i.getSubtotal();
        System.out.printf("[2] Tạm tính: %,.0f VND%n", s);
        return s;
    }

    protected void updateInventory(Order order) {
        System.out.println("[6] Cập nhật tồn kho cho " + order.getItems().size() + " sản phẩm");
    }

    protected void printReceipt(Order order) {
        System.out.printf("[8] HÓA ĐƠN %s - KH: %s - TỔNG: %,.0f VND%n",
                order.getId(), order.getCustomerName(), order.getTotal());
    }

    // ===== Bước thay đổi (abstract) =====
    protected abstract String getPaymentMethodName();
    protected abstract double calculateShippingFee(Order order, double amount);
    protected abstract void processPayment(Order order);

    // ===== Hook (mặc định, có thể ghi đè) =====
    protected double applyDiscount(Order order, double amount) {
        double d = order.isVip() ? amount * 0.05 : 0;
        System.out.printf("[3] Giảm giá: %,.0f VND%n", d);
        return d;
    }

    protected void notifyCustomer(Order order) {
        System.out.println("[7] Gửi email xác nhận tới " + order.getCustomerName());
    }
}