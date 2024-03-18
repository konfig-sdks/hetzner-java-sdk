

# VolumesCreateVolumeRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**automount** | **Boolean** | Auto-mount Volume after attach. &#x60;server&#x60; must be provided. |  [optional] |
|**format** | **String** | Format Volume after creation. One of: &#x60;xfs&#x60;, &#x60;ext4&#x60; |  [optional] |
|**labels** | **Object** | User-defined labels (key-value pairs) |  [optional] |
|**location** | **String** | Location to create the Volume in (can be omitted if Server is specified) |  [optional] |
|**name** | **String** | Name of the volume |  |
|**server** | **Long** | Server to which to attach the Volume once it&#39;s created (Volume will be created in the same Location as the server) |  [optional] |
|**size** | **Integer** | Size of the Volume in GB |  |



