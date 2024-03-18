

# ServersGetServerResponseServerServerType


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**description** | **String** | Description of the Server type |  |
|**architecture** | [**ArchitectureEnum**](#ArchitectureEnum) | Type of cpu architecture |  |
|**cores** | **Double** | Number of cpu cores a Server of this type will have |  |
|**cpuType** | [**CpuTypeEnum**](#CpuTypeEnum) | Type of cpu |  |
|**deprecated** | **Boolean** | This field is deprecated. Use the deprecation object instead |  |
|**deprecation** | [**DeprecationInfoProperty9**](DeprecationInfoProperty9.md) |  |  [optional] |
|**disk** | **Double** | Disk size a Server of this type will have in GB |  |
|**id** | **Long** | ID of the Server type |  |
|**includedTraffic** | **Long** | Free traffic per month in bytes |  |
|**memory** | **Double** | Memory a Server of this type will have in GB |  |
|**name** | **String** | Unique identifier of the Server type |  |
|**prices** | [**List&lt;ServersGetServerResponseServerServerTypePricesInner&gt;**](ServersGetServerResponseServerServerTypePricesInner.md) | Prices in different Locations |  |
|**storageType** | [**StorageTypeEnum**](#StorageTypeEnum) | Type of Server boot drive. Local has higher speed. Network has better availability. |  |



## Enum: ArchitectureEnum

| Name | Value |
|---- | -----|
| X86 | &quot;x86&quot; |
| ARM | &quot;arm&quot; |



## Enum: CpuTypeEnum

| Name | Value |
|---- | -----|
| SHARED | &quot;shared&quot; |
| DEDICATED | &quot;dedicated&quot; |



## Enum: StorageTypeEnum

| Name | Value |
|---- | -----|
| LOCAL | &quot;local&quot; |
| NETWORK | &quot;network&quot; |



