

# ServerActionsCreateImageRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**description** | **String** | Description of the Image, will be auto-generated if not set |  [optional] |
|**labels** | [**ServerActionsCreateImageRequestLabels**](ServerActionsCreateImageRequestLabels.md) |  |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of Image to create. |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| SNAPSHOT | &quot;snapshot&quot; |
| BACKUP | &quot;backup&quot; |



