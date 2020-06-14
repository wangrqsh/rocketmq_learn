http://jm.taobao.org/2017/01/12/rocketmq-quick-start-in-10-minutes/

http://rocketmq.apache.org/docs/quick-start/

https://www.slideshare.net/

https://juejin.im/post/5e8aaf6cf265da47b1778de2

https://www.cnblogs.com/Joeris/articles/10999373.html

systemctl stop firewalld

systemctl  disable  firewalld

内存小于8G 记得修改 runbroker.sh或 .cmd   java vm内存配置，否则启动失败

nohup sh mqnamesrv &

nohup sh mqbroker -n localhost:9876 autoCreateTopicEnable=true &

产生消息时,报错说 no router info of this topic 问题则需要提前创建topic,因为autoCreateTopicEnable=true并不生效

sh mqadmin updateTopic  -b 192.168.1.114:10911  -t  DemoTopic  -n 192.168.1.114:9876

创建topic,注意一定要带端口号,ip:端口

如果创建时报错说:

rocketMQ：unable to calculate a request signature. error=Algorithm HmacSHA1 not available

修改 vi  /rocketmq/bin/tools.sh

在 ${JAVA_HOME}/jre/lib/ext 后加上ext文件夹的绝对路径,如

JAVA_OPT="${JAVA_OPT} -Djava.ext.dirs=${BASE_DIR}/lib:${JAVA_HOME}/jre/lib/ext:/usr/java/jdk1.8.0_65/jre/lib/ext" 

再次创建topic.


mq控制台web面板
rocketmq-externals-master/rocketmq-console



Raft算法选举,自旋锁,重入锁



https://github.com/apache/rocketmq/blob/master/docs/cn/best_practice.md

https://www.jianshu.com/p/345aaa18f71d

https://juejin.im/post/5e8aaf6cf265da47b1778de2

https://www.linuxjournal.com/article/6345

https://www.jianshu.com/c/8cfe32491344



http://jm.taobao.org/



