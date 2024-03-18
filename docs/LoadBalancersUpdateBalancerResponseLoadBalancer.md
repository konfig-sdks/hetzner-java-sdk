

# LoadBalancersUpdateBalancerResponseLoadBalancer


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**algorithm** | [**LoadBalancersGetAllResponseLoadBalancersInnerAlgorithm**](LoadBalancersGetAllResponseLoadBalancersInnerAlgorithm.md) |  |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**includedTraffic** | **Long** | Free Traffic for the current billing period in bytes |  |
|**ingoingTraffic** | **Long** | Inbound Traffic for the current billing period in bytes |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**loadBalancerType** | [**LoadBalancersUpdateBalancerResponseLoadBalancerLoadBalancerType**](LoadBalancersUpdateBalancerResponseLoadBalancerLoadBalancerType.md) |  |  |
|**location** | [**LoadBalancersUpdateBalancerResponseLoadBalancerLocation**](LoadBalancersUpdateBalancerResponseLoadBalancerLocation.md) |  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**outgoingTraffic** | **Long** | Outbound Traffic for the current billing period in bytes |  |
|**privateNet** | [**List&lt;LoadBalancersUpdateBalancerResponseLoadBalancerPrivateNetInner&gt;**](LoadBalancersUpdateBalancerResponseLoadBalancerPrivateNetInner.md) | Private networks information |  |
|**protection** | [**LoadBalancersUpdateBalancerResponseLoadBalancerProtection**](LoadBalancersUpdateBalancerResponseLoadBalancerProtection.md) |  |  |
|**publicNet** | [**LoadBalancersUpdateBalancerResponseLoadBalancerPublicNet**](LoadBalancersUpdateBalancerResponseLoadBalancerPublicNet.md) |  |  |
|**services** | [**List&lt;LoadBalancerService4&gt;**](LoadBalancerService4.md) | List of services that belong to this Load Balancer |  |
|**targets** | [**List&lt;LoadBalancerTarget4&gt;**](LoadBalancerTarget4.md) | List of targets that belong to this Load Balancer |  |



