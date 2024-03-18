

# LoadBalancersGetByIdResponseLoadBalancer


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**algorithm** | [**LoadBalancersGetAllResponseLoadBalancersInnerAlgorithm**](LoadBalancersGetAllResponseLoadBalancersInnerAlgorithm.md) |  |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**includedTraffic** | **Long** | Free Traffic for the current billing period in bytes |  |
|**ingoingTraffic** | **Long** | Inbound Traffic for the current billing period in bytes |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**loadBalancerType** | [**LoadBalancersGetByIdResponseLoadBalancerLoadBalancerType**](LoadBalancersGetByIdResponseLoadBalancerLoadBalancerType.md) |  |  |
|**location** | [**LoadBalancersGetByIdResponseLoadBalancerLocation**](LoadBalancersGetByIdResponseLoadBalancerLocation.md) |  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**outgoingTraffic** | **Long** | Outbound Traffic for the current billing period in bytes |  |
|**privateNet** | [**List&lt;LoadBalancersGetByIdResponseLoadBalancerPrivateNetInner&gt;**](LoadBalancersGetByIdResponseLoadBalancerPrivateNetInner.md) | Private networks information |  |
|**protection** | [**LoadBalancersGetByIdResponseLoadBalancerProtection**](LoadBalancersGetByIdResponseLoadBalancerProtection.md) |  |  |
|**publicNet** | [**LoadBalancersGetByIdResponseLoadBalancerPublicNet**](LoadBalancersGetByIdResponseLoadBalancerPublicNet.md) |  |  |
|**services** | [**List&lt;LoadBalancerService3&gt;**](LoadBalancerService3.md) | List of services that belong to this Load Balancer |  |
|**targets** | [**List&lt;LoadBalancerTarget3&gt;**](LoadBalancerTarget3.md) | List of targets that belong to this Load Balancer |  |



