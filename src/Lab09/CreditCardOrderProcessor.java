package Lab09;

/** Thanh toán thẻ tín dụng. */
public class CreditCardOrderProcessor extends OrderProcessor {
    private final String cardNumber;
    private final double balance;

    public CreditCardOrderProcessor(String cardNumber, double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }
    @Override protected String getPaymentMethodName() { return "THẺ TÍN DỤNG"; }

    @Override protected double calculateShippingFee(Order o, double amount) {
        double fee = 15_000;
        System.out.printf("[4] Phí vận chuyển (giao nhanh): %,.0f VND%n", fee);
        return fee;
    }

    @Override protected void processPayment(Order o) {
        if (cardNumber == null || cardNumber.length() != 16)
            throw new IllegalArgumentException("Số thẻ không hợp lệ");
        double charge = o.getTotal() * 1.02; // phí cổng thanh toán 2%
        if (charge > balance)
            throw new IllegalStateException("Số dư thẻ không đủ");
        o.setTotal(charge);
        System.out.printf("[5] Trừ thẻ ****%s: %,.0f VND (đã gồm phí 2%%)%n",
                cardNumber.substring(12), charge);
    }
}