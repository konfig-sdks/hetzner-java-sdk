

# PrimaryIPProperty2


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**assigneeId** | **Long** | ID of the resource the Primary IP is assigned to, null if it is not assigned at all |  |
|**assigneeType** | [**AssigneeTypeEnum**](#AssigneeTypeEnum) | Resource type the Primary IP can be assigned to |  |
|**autoDelete** | **Boolean** | Delete this Primary IP when the resource it is assigned to is deleted |  |
|**blocked** | **Boolean** | Whether the IP is blocked |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**datacenter** | [**PrimaryIPProperty2Datacenter**](PrimaryIPProperty2Datacenter.md) |  |  |
|**dnsPtr** | [**List&lt;PrimaryIPProperty2DnsPtrInner&gt;**](PrimaryIPProperty2DnsPtrInner.md) | Array of reverse DNS entries |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**ip** | **String** | IP address. |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**protection** | [**PrimaryIPProperty2Protection**](PrimaryIPProperty2Protection.md) |  |  |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the Primary IP |  |



## Enum: AssigneeTypeEnum

| Name | Value |
|---- | -----|
| SERVER | &quot;server&quot; |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| IPV4 | &quot;ipv4&quot; |
| IPV6 | &quot;ipv6&quot; |



