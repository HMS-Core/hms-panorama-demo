# HMS PanoramaKit Demo

## Table of Contents
 * [Introduction](#introduction)
 * [Getting Started](#getting-started)
 * [Supported Environments](#supported-environments)
 * [Result](#result)
 * [License](#license)

## Introduction
The PanoramaKitDemo app demonstrates some samples to call the panorama view.
Some interfaces of panorama sdk are added in new versions, Please make sure the version is correct when compiling.

[Read more about HMS Panorama Kit](https://developer.huawei.com/consumer/cn/hms/huawei-panoramakit).

## Getting Started
   1. Check whether the Android studio development environment is ready. Open the sample code project directory with file "build.gradle" in Android Studio. Run TestApp on your divice or simulator which have installed latest Huawei Mobile Service(HMS).
   2. Register a [HUAWEI account](https://developer.huawei.com/consumer/en/).
   3. Create an app and configure the app information in AppGallery Connect.See details: [HUAWEI Panorama Service Development Preparation],Please find the 'App Development/preparations' chapter through this link:(https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/panorama-introduction).
   4. To build this demo, please first import the demo in the Android Studio (3.5+).
   5. Configure the sample code:
     (1) Download the file "agconnect-services.json" of the app on AGC, and add the file to the app root directory(\app) of the demo. 
     (2) Change the value of applicationid in the app-level build.gradle file of the sample project to the package name of your app.
   6. Run the sample on your Android device or emulator.

## Supported Environments
- Android Studio 3.5.0 or a later version is recommended.
- Java SDK 1.7 or later.
- HMS Core APK 4.0.0.300 or later.
- HMS Core Panorama SDK 4.0.4.300 or later.
- Android 5.0 or later.
- Internet is required for upgrade.

## Result
   <img src="https://github.com/HMS-Core/hms-panoram-demo/blob/master/images/result.JPG" width=250 title="Panoramakit Demo" div align=center border=5>

## Question or issues
If you want to evaluate more about HMS Core, [r/HMSCore on Reddit](https://www.reddit.com/r/HMSCore/) is for you to keep up with latest news about HMS Core, and to exchange insights with other developers.

If you have questions about how to use HMS samples, try the following options:
- [Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services) is the best place for any programming questions. Be sure to tag your question with 
**huawei-mobile-services**.
- [Huawei Developer Forum](https://forums.developer.huawei.com/forumPortal/en/home?fid=0101187876626530001) HMS Core Module is great for general questions, or seeking recommendations and opinions.

If you run into a bug in our samples, please submit an [issue](https://github.com/HMS-Core/hms-panoram-demo/issues) to the Repository. Even better you can submit a [Pull Request](https://github.com/HMS-Core/hms-panoram-demo/pulls) with a fix.

##  License
HMS PanoramaKit Demo is licensed under the [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
