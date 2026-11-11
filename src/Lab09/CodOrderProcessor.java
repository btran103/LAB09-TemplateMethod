package Lab09;

/** Thanh toán khi nhận hàng (COD). */
public class CodOrderProcessor extends OrderProcessor {
    @Override protected String getPaymentMethodName() { return "COD"; }

    @Override protected double calculateShippingFee(Order o, double amount) {
        double fee = amount >= 500_000 ? 0 : 30_000; // miễn phí ship từ 500k
        System.out.printf("[4] Phí vận chuyển COD: %,.0f VND%n", fee);
        return fee;
    }

    @Override protected void processPayment(Order o) {
        System.out.printf("[5] Ghi nhận thu hộ %,.0f VND khi giao hàng%n", o.getTotal());
    }
}