package com.training.structural.bridge.bad;

// Rigid inheritance tree structure
class SonyTV {}
class SonyTVWithBasicRemote extends SonyTV {}
class SonyTVWithSmartRemote extends SonyTV {}

class SamsungTV {}
class SamsungTVWithBasicRemote extends SamsungTV {}
class SamsungTVWithSmartRemote extends SamsungTV {}
// Adding 1 device and 1 remote requires writing 4-6 new classes!