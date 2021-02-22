集成步骤参考官方:
    https://github.com/didi/VirtualAPK
    
集成注意事项：
 1. Invalid ID 0x00000000;
 2. declared this activity in your AndroidManifest.xml;
 3. will be used in the current plugin must be included in the host app...；
 4. 等等...
 
如果不想看到以上几个恶心的报错，一定要注意的几件事：
    1. 不管是宿主还是插件，新建的Activity最好另起名字，包括布局文件，命名不能重复。特别是插件的Activity和宿主不能重名！
    2. 编译最好使用gradlew clean assemblePlugin的方式(mac前面加./)，也可以通过gradle -》 tasks -》 build -》assemblePlugin，一定不能通过直接build apk的方式进行编译。
    3. gradle版本，尽量使用官方demo使用的版本，不然容易出现各种编译问题或者兼容问题，官方也有在wiki上提到，尽量不要改动版本。
    4，宿主和插件的gradle.properties必须要配置：android.useDexArchive=false，否则build时会报错。
    5. 不同插件的packageId需要不同。
    6. 一定不要直接在宿主的工程目录以增加组件的方式创建插件，编译出来的插件无法跳转。
    
有个很重要的建议:
    不要直接去gradlew clean assemblePlugin，先尝试build普通的apk，如果可以正常build没有报错，再去执行gradlew clean assemblePlugin。有时候项目有问题，执行gradlew不会报错，但
    实际使用插件是有问题的。
    
目前一番集成下来，感觉这个框架集成需要非常小心，限制的地方非常多：
    1. 资源及类重名问题。
    2. gradle版本问题。当前推荐的版本太低，无法使用kt，升级版本后会有各种报错(这里坑很深)，即使最后编译成功，跳转插件也是异常的。
    3. 以组件创建的插件无法跳转问题(如项目中的pluginin组件)。这样需要开启两个AS来回切换开发，不太方便。
    一旦报错，根据报错信息基本没什么头绪，只能一遍一遍检查配置，对比demo猜测问题点。
    
   总的来说，这框架用起来不是那么爽，就像干架前给了把剑，拿上手发现还不能随便握，得熟读下宝典... 各种限制，各种问题，最好还是要深度读下源码，抄袭一套自己的出来。真直接用于生产环境出了问题很难定位问题。
现在多数项目都用kotlin开发，需要的gradle版本高于demo的3.0.0，官方推荐的版本根本没法用。当然你可以尝试升级gradle... 但最后你9.9成要么乖乖回到demo推荐版本重新集成，要么直接回到他demo上玩...
    
        
        
另外腾讯新出了个shadow的插件化框架，有空我也要去踩下坑，看下深不深。
   
   