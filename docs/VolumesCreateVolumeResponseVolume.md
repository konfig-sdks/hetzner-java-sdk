

# VolumesCreateVolumeResponseVolume


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**format** | **String** | Filesystem of the Volume if formatted on creation, null if not formatted on creation |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**linuxDevice** | **String** | Device path on the file system for the Volume |  |
|**location** | [**VolumesCreateVolumeResponseVolumeLocation**](VolumesCreateVolumeResponseVolumeLocation.md) |  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**protection** | [**VolumesCreateVolumeResponseVolumeProtection**](VolumesCreateVolumeResponseVolumeProtection.md) |  |  |
|**server** | **Long** | ID of the Server the Volume is attached to, null if it is not attached at all |  |
|**size** | **Double** | Size in GB of the Volume |  |
|**status** | [**StatusEnum**](#StatusEnum) | Current status of the Volume |  |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| CREATING | &quot;creating&quot; |
| AVAILABLE | &quot;available&quot; |



