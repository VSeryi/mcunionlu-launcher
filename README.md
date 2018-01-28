# mcunionlu-launcher
[2018-01-28T23:06:32.743] java.lang.NoClassDefFoundError: com/sun/webkit/network/CookieManager
[2018-01-28T23:06:32.744]       at com.machinepublishers.jbrowserdriver.JBrowserDriverServer.main(JBrowserDriverServer.java:80)
[2018-01-28T23:06:32.744] Caused by: java.lang.ClassNotFoundException: com.sun.webkit.network.CookieManager
[2018-01-28T23:06:32.744]       at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
[2018-01-28T23:06:32.744]       at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
[2018-01-28T23:06:32.745]       at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
[2018-01-28T23:06:32.745]       at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
[2018-01-28T23:06:32.745]       ... 1 more
[2018-01-28T23:06:32.926] java.lang.NoClassDefFoundError: com/sun/webkit/network/CookieManager
[2018-01-28T23:06:32.926]       at com.machinepublishers.jbrowserdriver.JBrowserDriverServer.main(JBrowserDriverServer.java:80)
[2018-01-28T23:06:32.926] Caused by: java.lang.ClassNotFoundException: com.sun.webkit.network.CookieManager
[2018-01-28T23:06:32.926]       at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
[2018-01-28T23:06:32.926]       at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
[2018-01-28T23:06:32.926]       at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
[2018-01-28T23:06:32.926]       at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
[2018-01-28T23:06:32.926]       ... 1 more
Exception in thread "main" org.openqa.selenium.WebDriverException: Could not launch browser.
Build info: version: 'unknown', revision: 'unknown', time: 'unknown'
System info: host: 'mineos', ip: '127.0.1.1', os.name: 'Linux', os.arch: 'amd64', os.version: '3.16.0-5-amd64', java.version: '1.8.0_131'
Driver info: driver.version: JBrowserDriver
        at com.machinepublishers.jbrowserdriver.Util.handleException(Util.java:139)
        at com.machinepublishers.jbrowserdriver.JBrowserDriver.<init>(JBrowserDriver.java:344)
        at com.svaleror.modpackupdater.logic.Main.getLastVersionLink(Main.java:79)
        at com.svaleror.modpackupdater.logic.Main.main(Main.java:109)
Caused by: java.lang.IllegalStateException: Could not launch browser.
        ... 3 more

-->
openjfx
