

# LoadBalancerTarget


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**healthStatus** | [**List&lt;LoadBalancerTargetHealthStatusPropertyInner&gt;**](LoadBalancerTargetHealthStatusPropertyInner.md) | List of health statuses of the services on this target. Only present for target types \&quot;server\&quot; and \&quot;ip\&quot;. |  [optional] |
|**ip** | [**LoadBalancerTargetIPProperty**](LoadBalancerTargetIPProperty.md) |  |  [optional] |
|**labelSelector** | [**LoadBalancerTargetLabelSelectorProperty**](LoadBalancerTargetLabelSelectorProperty.md) |  |  [optional] |
|**server** | [**LoadBalancerTargetServerProperty**](LoadBalancerTargetServerProperty.md) |  |  [optional] |
|**targets** | [**List&lt;LoadBalancerTargetTarget&gt;**](LoadBalancerTargetTarget.md) | List of resolved label selector target Servers. Only present for type \&quot;label_selector\&quot;. |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the resource |  |
|**usePrivateIp** | **Boolean** | Use the private network IP instead of the public IP. Only present for target types \&quot;server\&quot; and \&quot;label_selector\&quot;. |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| SERVER | &quot;server&quot; |
| LABEL_SELECTOR | &quot;label_selector&quot; |
| IP | &quot;ip&quot; |



