package Lab09;

public class Main {
    public static void main(String[] args) {
        // Test 1: COD, khách thường, dưới 500k -> tính phí ship
        Order o1 = new Order("DH001", "Nguyen Van A", false);
        o1.addItem(new OrderItem("Chuột không dây", 2, 150_000));
        new CodOrderProcessor().processOrder(o1);

        // Test 2: Thẻ tín dụng, khách VIP
        Order o2 = new Order("DH002", "Tran Thi B", true);
        o2.addItem(new OrderItem("Bàn phím cơ", 1, 1_200_000));
        new CreditCardOrderProcessor("1234567812345678", 5_000_000).processOrder(o2);

        // Test 3: Ví điện tử (ghi đè hook)
        Order o3 = new Order("DH003", "Le Van C", true);
        o3.addItem(new OrderItem("Tai nghe", 1, 800_000));
        new EWalletOrderProcessor("0905123456").processOrder(o3);

        // Test 4: Lỗi - thẻ không đủ số dư
        Order o4 = new Order("DH004", "Pham Thi D", false);
        o4.addItem(new OrderItem("Màn hình", 1, 3_000_000));
        new CreditCardOrderProcessor("1111222233334444", 1_000_000).processOrder(o4);

        // Test 5: Lỗi - đơn rỗng
        new CodOrderProcessor().processOrder(new Order("DH005", "Hoang E", false));
    }
}