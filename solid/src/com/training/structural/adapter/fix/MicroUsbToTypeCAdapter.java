package com.training.structural.adapter.fix;
// Target Interface (What the client expects)
interface TypeCPhone {
    void chargeWithTypeC();
}

// Adaptee (The old/different interface)
class MicroUsbPhone {
    void chargeWithMicroUsb() { System.out.println("Charging with Micro USB..."); }
}

// Adapter
class MicroUsbToTypeCAdapter implements TypeCPhone {
    private final MicroUsbPhone phone;

    public MicroUsbToTypeCAdapter(MicroUsbPhone phone) {
        this.phone = phone;
    }

    @Override
    public void chargeWithTypeC() {
        System.out.print("Adapter converts Type-C to Micro-USB -> ");
        phone.chargeWithMicroUsb();
    }
}