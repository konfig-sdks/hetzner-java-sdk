

# LoadBalancerActionsUpdateServiceRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**destinationPort** | **Integer** | Port the Load Balancer will balance to |  [optional] |
|**healthCheck** | [**UpdateLoadBalancerServiceHealthCheckProperty**](UpdateLoadBalancerServiceHealthCheckProperty.md) |  |  [optional] |
|**http** | [**LoadBalancerServiceHTTPProperty2**](LoadBalancerServiceHTTPProperty2.md) |  |  [optional] |
|**listenPort** | **Integer** | Port the Load Balancer listens on |  |
|**protocol** | [**ProtocolEnum**](#ProtocolEnum) | Protocol of the Load Balancer |  [optional] |
|**proxyprotocol** | **Boolean** | Is Proxyprotocol enabled or not |  [optional] |



## Enum: ProtocolEnum

| Name | Value |
|---- | -----|
| TCP | &quot;tcp&quot; |
| HTTP | &quot;http&quot; |
| HTTPS | &quot;https&quot; |



