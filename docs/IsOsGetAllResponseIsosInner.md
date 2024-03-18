

# IsOsGetAllResponseIsosInner


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**description** | **String** | Description of the ISO |  |
|**architecture** | [**ArchitectureEnum**](#ArchitectureEnum) | Type of cpu architecture this iso is compatible with. Null indicates no restriction on the architecture (wildcard). |  |
|**deprecation** | [**DeprecationInfoProperty**](DeprecationInfoProperty.md) |  |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**name** | **String** | Unique identifier of the ISO. Only set for public ISOs |  |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the ISO |  |



## Enum: ArchitectureEnum

| Name | Value |
|---- | -----|
| X86 | &quot;x86&quot; |
| ARM | &quot;arm&quot; |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| PUBLIC | &quot;public&quot; |
| PRIVATE | &quot;private&quot; |



