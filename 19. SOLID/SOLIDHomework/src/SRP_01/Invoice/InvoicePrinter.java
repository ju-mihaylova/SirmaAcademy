package SRP_01.Invoice;

public class InvoicePrinter {

    public void print(Invoice invoice) {
        System.out.println("Invoice for " + invoice.getCustomerName() + ": $" + invoice.getAmount());
    }
}
