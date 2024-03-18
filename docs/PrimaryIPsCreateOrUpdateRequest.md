

# PrimaryIPsCreateOrUpdateRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**assigneeId** | **Long** | ID of the resource the Primary IP should be assigned to. Omitted if it should not be assigned. |  [optional] |
|**assigneeType** | [**AssigneeTypeEnum**](#AssigneeTypeEnum) | Resource type the Primary IP can be assigned to |  |
|**autoDelete** | **Boolean** | Delete the Primary IP when the Server it is assigned to is deleted. |  [optional] |
|**datacenter** | **String** | ID or name of Datacenter the Primary IP will be bound to. Needs to be omitted if &#x60;assignee_id&#x60; is passed. |  [optional] |
|**labels** | **Object** | User-defined labels (key-value pairs) |  [optional] |
|**name** | **String** |  |  |
|**type** | [**TypeEnum**](#TypeEnum) | Primary IP type |  |



## Enum: AssigneeTypeEnum

| Name | Value |
|---- | -----|
| SERVER | &quot;server&quot; |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| IPV4 | &quot;ipv4&quot; |
| IPV6 | &quot;ipv6&quot; |



