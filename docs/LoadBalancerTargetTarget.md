

# LoadBalancerTargetTarget


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**healthStatus** | [**List&lt;LoadBalancerTargetHealthStatusPropertyInner1&gt;**](LoadBalancerTargetHealthStatusPropertyInner1.md) | List of health statuses of the services on this target. Only present for target types \&quot;server\&quot; and \&quot;ip\&quot;. |  [optional] |
|**server** | [**LoadBalancerTargetServerProperty1**](LoadBalancerTargetServerProperty1.md) |  |  [optional] |
|**type** | **String** | Type of the resource. Here always \&quot;server\&quot;. |  [optional] |
|**usePrivateIp** | **Boolean** | Use the private network IP instead of the public IP. Only present for target types \&quot;server\&quot; and \&quot;label_selector\&quot;. |  [optional] |



