

# LoadBalancersCreateLoadBalancerRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**algorithm** | [**LoadBalancerAlgorithmProperty**](LoadBalancerAlgorithmProperty.md) |  |  [optional] |
|**labels** | [**LoadBalancersCreateLoadBalancerRequestLabels**](LoadBalancersCreateLoadBalancerRequestLabels.md) |  |  [optional] |
|**loadBalancerType** | **String** | ID or name of the Load Balancer type this Load Balancer should be created with |  |
|**location** | **String** | ID or name of Location to create Load Balancer in |  [optional] |
|**name** | **String** | Name of the Load Balancer |  |
|**network** | **Long** | ID of the network the Load Balancer should be attached to on creation |  [optional] |
|**networkZone** | **String** | Name of network zone |  [optional] |
|**publicInterface** | **Boolean** | Enable or disable the public interface of the Load Balancer |  [optional] |
|**services** | [**List&lt;LoadBalancerService&gt;**](LoadBalancerService.md) | Array of services |  [optional] |
|**targets** | [**List&lt;LoadBalancerTarget&gt;**](LoadBalancerTarget.md) | Array of targets |  [optional] |



