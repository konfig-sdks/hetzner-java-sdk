

# UpdateLoadBalancerServiceHealthCheckProperty

Service health check

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**http** | [**UpdateLoadBalancerServiceHealthCheckPropertyHttp**](UpdateLoadBalancerServiceHealthCheckPropertyHttp.md) |  |  [optional] |
|**interval** | **Integer** | Time interval in seconds health checks are performed |  [optional] |
|**port** | **Integer** | Port the health check will be performed on |  [optional] |
|**protocol** | [**ProtocolEnum**](#ProtocolEnum) | Type of the health check |  [optional] |
|**retries** | **Integer** | Unsuccessful retries needed until a target is considered unhealthy; an unhealthy target needs the same number of successful retries to become healthy again |  [optional] |
|**timeout** | **Integer** | Time in seconds after an attempt is considered a timeout |  [optional] |



## Enum: ProtocolEnum

| Name | Value |
|---- | -----|
| TCP | &quot;tcp&quot; |
| HTTP | &quot;http&quot; |



