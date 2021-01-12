package com.example.ble_status;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** BleStatusPlugin */
public class BleStatusPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  private BluetoothManager mBluetoothManager;
  private BluetoothAdapter mBluetoothAdapter;

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    mBluetoothManager = (BluetoothManager) flutterPluginBinding.getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
    mBluetoothAdapter = mBluetoothManager.getAdapter();

    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "ble_status");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("isOn")) {
      result.success(mBluetoothAdapter.isEnabled());
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
