

# FloatingIPsCreateNewIpRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**description** | **String** |  |  [optional] |
|**homeLocation** | **String** | Home Location (routing is optimized for that Location). Only optional if Server argument is passed. |  [optional] |
|**labels** | **Object** | User-defined labels (key-value pairs) |  [optional] |
|**name** | **String** |  |  [optional] |
|**server** | **Long** | ID of the Server to assign the Floating IP to |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Floating IP type |  |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| IPV4 | &quot;ipv4&quot; |
| IPV6 | &quot;ipv6&quot; |



