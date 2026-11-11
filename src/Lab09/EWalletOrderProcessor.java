package Lab09;

/** Thanh toán ví điện tử - ghi đè cả hook (khuyến mãi riêng + thông báo SMS). */
public class EWalletOrderProcessor extends OrderProcessor {
    private final String phone;
    public EWalletOrderProcessor(String phone) { this.phone = phone; }

    @Override protected String getPaymentMethodName() { return "VÍ ĐIỆN TỬ"; }

    @Override protected double applyDiscount(Order o, double amount) {
        double d = amount * 0.03 + (o.isVip() ? amount * 0.05 : 0); // ưu đãi ví
        System.out.printf("[3] Giảm giá (ưu đãi ví): %,.0f VND%n", d);
        return d;
    }
    @Override protected double calculateShippingFee(Order o, double amount) {
        System.out.println("[4] Miễn phí vận chuyển (ưu đãi ví)");
        return 0;
    }
    @Override protected void processPayment(Order o) {
        System.out.printf("[5] Trừ ví %s: %,.0f VND%n", phone, o.getTotal());
    }
    @Override protected void notifyCustomer(Order o) {
        System.out.println("[7] Gửi SMS xác nhận tới " + phone);
    }
}