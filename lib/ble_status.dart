import 'dart:async';

import 'package:flutter/services.dart';

class BleStatus {
  static const MethodChannel _channel = const MethodChannel('ble_status');

  static Future<bool> get isOn => _channel.invokeMethod('isOn').then<bool>((d) => d);
}
