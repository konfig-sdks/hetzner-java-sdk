

# LoadBalancerActionsRemoveTargetRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**ip** | [**LoadBalancerTargetIPProperty2**](LoadBalancerTargetIPProperty2.md) |  |  [optional] |
|**labelSelector** | [**LoadBalancerActionsRemoveTargetRequestLabelSelector**](LoadBalancerActionsRemoveTargetRequestLabelSelector.md) |  |  [optional] |
|**server** | [**LoadBalancerActionsRemoveTargetRequestServer**](LoadBalancerActionsRemoveTargetRequestServer.md) |  |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the resource |  |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| SERVER | &quot;server&quot; |
| LABEL_SELECTOR | &quot;label_selector&quot; |
| IP | &quot;ip&quot; |



