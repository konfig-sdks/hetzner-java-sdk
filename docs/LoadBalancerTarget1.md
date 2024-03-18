

# LoadBalancerTarget1


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**healthStatus** | [**List&lt;LoadBalancerTargetHealthStatusPropertyInner2&gt;**](LoadBalancerTargetHealthStatusPropertyInner2.md) | List of health statuses of the services on this target. Only present for target types \&quot;server\&quot; and \&quot;ip\&quot;. |  [optional] |
|**ip** | [**LoadBalancerTargetIPProperty3**](LoadBalancerTargetIPProperty3.md) |  |  [optional] |
|**labelSelector** | [**LoadBalancerTargetLabelSelectorProperty1**](LoadBalancerTargetLabelSelectorProperty1.md) |  |  [optional] |
|**server** | [**LoadBalancerTargetServerProperty2**](LoadBalancerTargetServerProperty2.md) |  |  [optional] |
|**targets** | [**List&lt;LoadBalancerTargetTarget1&gt;**](LoadBalancerTargetTarget1.md) | List of resolved label selector target Servers. Only present for type \&quot;label_selector\&quot;. |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the resource |  |
|**usePrivateIp** | **Boolean** | Use the private network IP instead of the public IP. Only present for target types \&quot;server\&quot; and \&quot;label_selector\&quot;. |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| SERVER | &quot;server&quot; |
| LABEL_SELECTOR | &quot;label_selector&quot; |
| IP | &quot;ip&quot; |



