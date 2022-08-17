//
// Created by favortube on 2022/8/9.
//
#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>
//#import <Cordova/CDVPlugin.h>

@interface node : CDVPlugin {
  // Member variables go here.
}
- (void)getVersion:(CDVInvokedUrlCommand*)command;
- (void)start:(CDVInvokedUrlCommand*)command;
@end
