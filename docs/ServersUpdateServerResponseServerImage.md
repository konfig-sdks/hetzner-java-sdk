

# ServersUpdateServerResponseServerImage

Image the server is based on.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**description** | **String** | Description of the Image |  |
|**architecture** | [**ArchitectureEnum**](#ArchitectureEnum) | Type of cpu architecture this image is compatible with. |  |
|**boundTo** | **Long** | ID of Server the Image is bound to. Only set for Images of type &#x60;backup&#x60;. |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**createdFrom** | [**ServersUpdateServerResponseServerImageCreatedFrom**](ServersUpdateServerResponseServerImageCreatedFrom.md) |  |  |
|**deleted** | **String** | Point in time where the Image was deleted (in ISO-8601 format) |  |
|**deprecated** | **String** | Point in time when the Image is considered to be deprecated (in ISO-8601 format) |  |
|**diskSize** | **Double** | Size of the disk contained in the Image in GB |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**imageSize** | **Double** | Size of the Image file in our storage in GB. For snapshot Images this is the value relevant for calculating costs for the Image. |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**name** | **String** | Unique identifier of the Image. This value is only set for system Images. |  |
|**osFlavor** | [**OsFlavorEnum**](#OsFlavorEnum) | Flavor of operating system contained in the Image |  |
|**osVersion** | **String** | Operating system version |  |
|**protection** | [**ServersUpdateServerResponseServerImageProtection**](ServersUpdateServerResponseServerImageProtection.md) |  |  |
|**rapidDeploy** | **Boolean** | Indicates that rapid deploy of the Image is available |  [optional] |
|**status** | [**StatusEnum**](#StatusEnum) | Whether the Image can be used or if it&#39;s still being created or unavailable |  |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the Image |  |



## Enum: ArchitectureEnum

| Name | Value |
|---- | -----|
| X86 | &quot;x86&quot; |
| ARM | &quot;arm&quot; |



## Enum: OsFlavorEnum

| Name | Value |
|---- | -----|
| UBUNTU | &quot;ubuntu&quot; |
| CENTOS | &quot;centos&quot; |
| DEBIAN | &quot;debian&quot; |
| FEDORA | &quot;fedora&quot; |
| ROCKY | &quot;rocky&quot; |
| ALMA | &quot;alma&quot; |
| UNKNOWN | &quot;unknown&quot; |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| AVAILABLE | &quot;available&quot; |
| CREATING | &quot;creating&quot; |
| UNAVAILABLE | &quot;unavailable&quot; |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| SYSTEM | &quot;system&quot; |
| APP | &quot;app&quot; |
| SNAPSHOT | &quot;snapshot&quot; |
| BACKUP | &quot;backup&quot; |
| TEMPORARY | &quot;temporary&quot; |



