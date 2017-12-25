### activeMQ监控
#### 1. [JMX连接器-rmi](http://blog.csdn.net/java_huashan/article/details/36199603)

1.1 RMI连接器

* JMX API定义了一个标准连接器 - RMI Connector,它支持通过RMI远程访问一个MBeanServer

1.2 RMI连接器服务器的地址示例如下：

		service:jmx:rmi://localhost:1099/jndi/rmi://localhost:8899/myname
1.3 说明：

* 在这个JMXServiceURL中：
* 第一个rmi指的是rmi连接器，表示用连接器使用RMI传输协议，第二个rmi指定RMI注册RMI连服务接器存储存根
* localhost:1099: 这个是connector server的IP和端口，该部分是一个可选项，可以被省略掉。如果省略的话，则connector server会随机任意选择一个可用的端口
* /jndi/rmi://localhost:8899/myname: 这个是connector server的路径，表示Connector server的stud是使用JNDI API绑定在rmi://localhost:8899/myname这个地址上

创建一个RMI connector server
通常是提供一个RMI connector server的连接器地址，用JMXConnectorServerFactory.newJMXConnectorServer方法来创建RMI connector cerver
参考JMX API：http://docs.oracle.com/javase/8/docs/api/index.html?javax/management/remote/rmi/package-summary.html