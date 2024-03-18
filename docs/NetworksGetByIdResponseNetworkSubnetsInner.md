

# NetworksGetByIdResponseNetworkSubnetsInner


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**gateway** | **String** | Gateway for Servers attached to this subnet. For subnets of type Server this is always the first IP of the network IP range. |  |
|**ipRange** | **String** | Range to allocate IPs from. Must be a Subnet of the ip_range of the parent network object and must not overlap with any other subnets or with any destinations in routes. Minimum Network size is /30. We suggest that you pick a bigger Network with a /24 netmask. |  [optional] |
|**networkZone** | **String** | Name of Network zone. The Location object contains the &#x60;network_zone&#x60; property each Location belongs to. |  |
|**type** | [**TypeEnum**](#TypeEnum) | Type of Subnetwork |  |
|**vswitchId** | **Long** | ID of the robot vSwitch if the subnet is of type vswitch. |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| CLOUD | &quot;cloud&quot; |
| SERVER | &quot;server&quot; |
| VSWITCH | &quot;vswitch&quot; |



