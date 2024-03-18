

# FloatingIPsGetAllResponseFloatingIpsInner


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**description** | **String** | Description of the Resource. |  |
|**blocked** | **Boolean** | Whether the IP is blocked |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**dnsPtr** | [**List&lt;FloatingIPsGetAllResponseFloatingIpsInnerDnsPtrInner&gt;**](FloatingIPsGetAllResponseFloatingIpsInnerDnsPtrInner.md) | Array of reverse DNS entries |  |
|**homeLocation** | [**FloatingIPsGetAllResponseFloatingIpsInnerHomeLocation**](FloatingIPsGetAllResponseFloatingIpsInnerHomeLocation.md) |  |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**ip** | **String** | IP address. |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**protection** | [**FloatingIPsGetAllResponseFloatingIpsInnerProtection**](FloatingIPsGetAllResponseFloatingIpsInnerProtection.md) |  |  |
|**server** | **Long** | ID of the Server the Floating IP is assigned to, null if it is not assigned at all |  |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the Floating IP |  |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| IPV4 | &quot;ipv4&quot; |
| IPV6 | &quot;ipv6&quot; |



