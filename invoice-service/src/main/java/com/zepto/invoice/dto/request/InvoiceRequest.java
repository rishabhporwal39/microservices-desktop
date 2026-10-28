package com.zepto.invoice.dto.request;

public class InvoiceRequest {
    private String customerName;
    private int gst;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getGst() {
        return gst;
    }

    public void setGst(int gst) {
        this.gst = gst;
    }
}
