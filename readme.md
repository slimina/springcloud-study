# 微服务
将一个完整的应用从数据存储开始垂直拆分成多个不同的服务，每个服务都能独立部署、独立维护、独立扩展，
服务与服务间通过诸如RESTful API的方式互相调用。

框架如：

1.[spring cloud](http://projects.spring.io/spring-cloud/)

2.[dubbo](http://dubbo.apache.org/)

3.[motan](https://github.com/weibocom/motan)

4.[brpc](https://github.com/brpc/brpc)

5.[Tars](https://github.com/Tencent/Tars)

6.[grpc](https://github.com/grpc/grpc)

7.[rpcx](https://github.com/smallnest/rpcx)

## eureka
Spring Cloud Eureka是Spring Cloud Netflix项目下的服务治理模块。
## Spring Cloud Consul
Spring Cloud Consul项目是针对Consul的服务治理实现。Consul是一个分布式高可用的系统，
它包含多个组件，但是作为一个整体，在微服务架构中为我们的基础设施提供服务发现和服务配置的工具。
它包含了下面几个特性：
```$xslt
服务发现
健康检查
Key/Value存储
多数据中心
```
```$xslt
运行：
部署server端
docker run -d -p 12345:8500 -h node1 --name node1  consul agent -server -bootstrap-expect=1  -node=node1 -client 0.0.0.0 -ui
JOIN_IP="$(docker inspect -f '{{.NetworkSettings.IPAddress}}' node1)"
部署client端
docker run -d -p 8600:8600 -p 8500:8500 -p 8600:53/udp --name client2 -h client2 consul agent -ui -node=client2 -join $JOIN_IP 
```

