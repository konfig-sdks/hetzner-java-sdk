

# PlacementGroupNullableProperty3

The placement group the server is assigned to.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**servers** | **List&lt;Long&gt;** | Array of IDs of Servers that are part of this Placement Group |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the Placement Group |  |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| SPREAD | &quot;spread&quot; |



