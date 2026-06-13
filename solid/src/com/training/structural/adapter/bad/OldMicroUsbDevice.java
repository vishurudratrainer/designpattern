package com.training.structural.adapter.bad;

// Our system expects everything to use modern Type-C charging
interface TypeCTarget { void chargeWithTypeC(); }

// But we bought a third-party legacy library class that we cannot modify
class OldMicroUsbDevice {
    public void plugInMicroUsb() { System.out.println("Charging via old micro-USB cable."); }
}

// Problem: This code won't compile because the interfaces don't match!
 //TypeCTarget charger = new OldMicroUsbDevice();