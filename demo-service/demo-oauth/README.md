#Spring Cloud的oauth2认证实现

在spring cloud需要使用oauth2类实现多个服务的统一认证授权，通过向oauth服务发送某个类型的grant type进行集中认证和授权，从而获得access_token，而这个token是受其他微服务信任的。

`spring boot 版本`:1.4.3.RELEASE

`spring cloud 版本`:Camden.SR4

备注：

​	当时用spring boot版本为：`1.5.12.RELEASE`，spring cloud 版本为：`Dalston.SR5`的时候，各个服务启动不报错，也能正常请求token，但是使用token去请求API的时候，会提示token失效。(未解决)

三个服务分别为：

- `demo-oauth-gateway`:边界网关(端口：8765)
- `demo-auth-server`:oauth2认证授权中心(端口：8766)
- `demo-oauth-client`:普通服务，用来验证认证和授权(端口：8767)

### 启动服务

​	启动eureka-server服务，其他服务依次启动。

### 服务请求

URL：localhost:8765/auth/oauth/token

method：POST

Headers： Content-Type:application/x-www-form-urlencoded

​		    Authorization:Basic aXRhcHJpbDppdGFwcmls

Body:	grant_type:password

​		scope:ui

​		username:user

​		password:123456

注：`Authorization`中的`Basic`是固定的，`aXRhcHJpbDppdGFwcmls`对应的是代码中的client和secret中的“itapril”的base64编码，`aXRhcHJpbDppdGFwcmls`解码后为：`itapril:itapril`

请求localhost:8765/auth/oauth/token返回：

```json
{
  "access_token": "431349c5-9dde-4d0b-aa94-61a7d0bd2bd4",
  "token_type": "bearer",
  "refresh_token": "58d6e303-5822-4707-90fd-7814b7279096",
  "expires_in": 41576,
  "scope": "ui"
}
```

```json
"access_token": "431349c5-9dde-4d0b-aa94-61a7d0bd2bd4" 就是我们后面请求需要携带的token
```

#### 验证token

url：http://localhost:8765/client/getClientInfo    也可以使用8767端口

method：get

Headers:  Authorization:Bearer 431349c5-9dde-4d0b-aa94-61a7d0bd2bd4

`431349c5-9dde-4d0b-aa94-61a7d0bd2bd4`就是请求得到的token

返回：SUCCESS,授权成功user 则表示token没有问题

#### curl请求格式

获取token请求：

curl -H "Authorization:Basic aXRhcHJpbDppdGFwcmls" -d "grant_type=password&scope=ui&username=user&password=123456" localhost:8765/auth/oauth/token

返回：

{"access_token":"431349c5-9dde-4d0b-aa94-61a7d0bd2bd4","token_type":"bearer","refresh_token":"58d6e303-5822-4707-90fd-7814b7279096","expires_in":43199,"scope":"ui"}

api认证：

 curl -H "Authorization:Bearer 431349c5-9dde-4d0b-aa94-61a7d0bd2bd4" localhost:8765/client/getClientInfo

返回：

SUCCESS,授权成功user































































​	





