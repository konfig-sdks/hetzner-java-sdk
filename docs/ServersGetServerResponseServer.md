

# ServersGetServerResponseServer


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**backupWindow** | **String** | Time window (UTC) in which the backup will run, or null if the backups are not enabled |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**datacenter** | [**ServersGetServerResponseServerDatacenter**](ServersGetServerResponseServerDatacenter.md) |  |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**image** | [**ServersGetServerResponseServerImage**](ServersGetServerResponseServerImage.md) |  |  |
|**includedTraffic** | **Long** | Free Traffic for the current billing period in bytes |  |
|**ingoingTraffic** | **Long** | Inbound Traffic for the current billing period in bytes |  |
|**iso** | [**ServersGetServerResponseServerIso**](ServersGetServerResponseServerIso.md) |  |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**loadBalancers** | **List&lt;Long&gt;** | Load Balancer IDs assigned to the server. |  [optional] |
|**locked** | **Boolean** | True if Server has been locked and is not available to user |  |
|**name** | **String** | Name of the Server (must be unique per Project and a valid hostname as per RFC 1123) |  |
|**outgoingTraffic** | **Long** | Outbound Traffic for the current billing period in bytes |  |
|**placementGroup** | [**PlacementGroupNullableProperty2**](PlacementGroupNullableProperty2.md) |  |  [optional] |
|**primaryDiskSize** | **Double** | Size of the primary Disk |  |
|**privateNet** | [**List&lt;ServersGetServerResponseServerPrivateNetInner&gt;**](ServersGetServerResponseServerPrivateNetInner.md) | Private networks information |  |
|**protection** | [**ServersGetServerResponseServerProtection**](ServersGetServerResponseServerProtection.md) |  |  |
|**publicNet** | [**ServersGetServerResponseServerPublicNet**](ServersGetServerResponseServerPublicNet.md) |  |  |
|**rescueEnabled** | **Boolean** | True if rescue mode is enabled. Server will then boot into rescue system on next reboot |  |
|**serverType** | [**ServersGetServerResponseServerServerType**](ServersGetServerResponseServerServerType.md) |  |  |
|**status** | [**StatusEnum**](#StatusEnum) | Status of the Server |  |
|**volumes** | **List&lt;Long&gt;** | IDs of Volumes assigned to this Server |  [optional] |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| RUNNING | &quot;running&quot; |
| INITIALIZING | &quot;initializing&quot; |
| STARTING | &quot;starting&quot; |
| STOPPING | &quot;stopping&quot; |
| FALSE | &quot;false&quot; |
| DELETING | &quot;deleting&quot; |
| MIGRATING | &quot;migrating&quot; |
| REBUILDING | &quot;rebuilding&quot; |
| UNKNOWN | &quot;unknown&quot; |



