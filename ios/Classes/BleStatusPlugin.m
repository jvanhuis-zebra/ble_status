#import "BleStatusPlugin.h"

@interface BleStatusPlugin ()
@property (nonatomic, retain) CBCentralManager *centralManager;
@end

@implementation BleStatusPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"ble_status"
            binaryMessenger:[registrar messenger]];
  BleStatusPlugin* instance = [[BleStatusPlugin alloc] init];

  instance.centralManager = [[CBCentralManager alloc] initWithDelegate:instance queue:nil];
    
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"isOn" isEqualToString:call.method]) {
      if (@available(iOS 10.0, *)) {
          if(self.centralManager.state == CBManagerStatePoweredOn) {
              result(@(YES));
          } else {
              result(@(NO));
          }
      } else {
          if(self.centralManager.state == CBCentralManagerStatePoweredOn) {
              result(@(YES));
          } else {
              result(@(NO));
          }
      }
  } else {
    result(FlutterMethodNotImplemented);
  }
}

- (void)centralManagerDidUpdateState:(nonnull CBCentralManager *)central {
    <#code#>
}

@end
