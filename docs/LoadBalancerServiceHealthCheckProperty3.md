

# LoadBalancerServiceHealthCheckProperty3

Service health check

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**http** | [**LoadBalancerServiceHealthCheckProperty3Http**](LoadBalancerServiceHealthCheckProperty3Http.md) |  |  [optional] |
|**interval** | **Integer** | Time interval in seconds health checks are performed |  |
|**port** | **Integer** | Port the health check will be performed on |  |
|**protocol** | [**ProtocolEnum**](#ProtocolEnum) | Type of the health check |  |
|**retries** | **Integer** | Unsuccessful retries needed until a target is considered unhealthy; an unhealthy target needs the same number of successful retries to become healthy again |  |
|**timeout** | **Integer** | Time in seconds after an attempt is considered a timeout |  |



## Enum: ProtocolEnum

| Name | Value |
|---- | -----|
| TCP | &quot;tcp&quot; |
| HTTP | &quot;http&quot; |



