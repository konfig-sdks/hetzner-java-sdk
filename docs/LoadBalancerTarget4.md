

# LoadBalancerTarget4


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**healthStatus** | [**List&lt;LoadBalancerTargetHealthStatusPropertyInner8&gt;**](LoadBalancerTargetHealthStatusPropertyInner8.md) | List of health statuses of the services on this target. Only present for target types \&quot;server\&quot; and \&quot;ip\&quot;. |  [optional] |
|**ip** | [**LoadBalancerTargetIPProperty6**](LoadBalancerTargetIPProperty6.md) |  |  [optional] |
|**labelSelector** | [**LoadBalancerTargetLabelSelectorProperty4**](LoadBalancerTargetLabelSelectorProperty4.md) |  |  [optional] |
|**server** | [**LoadBalancerTargetServerProperty8**](LoadBalancerTargetServerProperty8.md) |  |  [optional] |
|**targets** | [**List&lt;LoadBalancerTargetTarget4&gt;**](LoadBalancerTargetTarget4.md) | List of resolved label selector target Servers. Only present for type \&quot;label_selector\&quot;. |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the resource |  |
|**usePrivateIp** | **Boolean** | Use the private network IP instead of the public IP. Only present for target types \&quot;server\&quot; and \&quot;label_selector\&quot;. |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| SERVER | &quot;server&quot; |
| LABEL_SELECTOR | &quot;label_selector&quot; |
| IP | &quot;ip&quot; |



