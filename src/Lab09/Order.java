package Lab09;

import java.util.ArrayList;
import java.util.List;

/** Đơn hàng: dữ liệu được các bước xử lý đọc/ghi. */
public class Order {
    private final String id;
    private final String customerName;
    private final boolean vip;
    private final List<OrderItem> items = new ArrayList<>();
    private double total;

    public Order(String id, String customerName, boolean vip) {
        this.id = id;
        this.customerName = customerName;
        this.vip = vip;
    }
    public void addItem(OrderItem item) { items.add(item); }
    public String getId() { return id; }
    public String getCustomerName() { return customerName; }
    public boolean isVip() { return vip; }
    public List<OrderItem> getItems() { return items; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}