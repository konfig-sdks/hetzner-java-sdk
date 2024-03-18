

# LoadBalancersCreateLoadBalancerResponseLoadBalancer


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**algorithm** | [**LoadBalancersGetAllResponseLoadBalancersInnerAlgorithm**](LoadBalancersGetAllResponseLoadBalancersInnerAlgorithm.md) |  |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**includedTraffic** | **Long** | Free Traffic for the current billing period in bytes |  |
|**ingoingTraffic** | **Long** | Inbound Traffic for the current billing period in bytes |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**loadBalancerType** | [**LoadBalancersCreateLoadBalancerResponseLoadBalancerLoadBalancerType**](LoadBalancersCreateLoadBalancerResponseLoadBalancerLoadBalancerType.md) |  |  |
|**location** | [**LoadBalancersCreateLoadBalancerResponseLoadBalancerLocation**](LoadBalancersCreateLoadBalancerResponseLoadBalancerLocation.md) |  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**outgoingTraffic** | **Long** | Outbound Traffic for the current billing period in bytes |  |
|**privateNet** | [**List&lt;LoadBalancersCreateLoadBalancerResponseLoadBalancerPrivateNetInner&gt;**](LoadBalancersCreateLoadBalancerResponseLoadBalancerPrivateNetInner.md) | Private networks information |  |
|**protection** | [**LoadBalancersCreateLoadBalancerResponseLoadBalancerProtection**](LoadBalancersCreateLoadBalancerResponseLoadBalancerProtection.md) |  |  |
|**publicNet** | [**LoadBalancersCreateLoadBalancerResponseLoadBalancerPublicNet**](LoadBalancersCreateLoadBalancerResponseLoadBalancerPublicNet.md) |  |  |
|**services** | [**List&lt;LoadBalancerService2&gt;**](LoadBalancerService2.md) | List of services that belong to this Load Balancer |  |
|**targets** | [**List&lt;LoadBalancerTarget2&gt;**](LoadBalancerTarget2.md) | List of targets that belong to this Load Balancer |  |



