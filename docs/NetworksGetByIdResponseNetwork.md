

# NetworksGetByIdResponseNetwork


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**servers** | **List&lt;Long&gt;** | Array of IDs of Servers attached to this Network |  |
|**created** | **String** | Point in time when the Network was created (in ISO-8601 format) |  |
|**exposeRoutesToVswitch** | **Boolean** | Indicates if the routes from this network should be exposed to the vSwitch connection. |  |
|**id** | **Long** | ID of the Network |  |
|**ipRange** | **String** | IPv4 prefix of the whole Network |  |
|**labels** | **Object** | User-defined labels (key-value pairs) |  |
|**loadBalancers** | **List&lt;Long&gt;** | Array of IDs of Load Balancers attached to this Network |  [optional] |
|**name** | **String** | Name of the Network |  |
|**protection** | [**NetworksGetByIdResponseNetworkProtection**](NetworksGetByIdResponseNetworkProtection.md) |  |  |
|**routes** | [**List&lt;NetworksGetByIdResponseNetworkRoutesInner&gt;**](NetworksGetByIdResponseNetworkRoutesInner.md) | Array of routes set in this Network |  |
|**subnets** | [**List&lt;NetworksGetByIdResponseNetworkSubnetsInner&gt;**](NetworksGetByIdResponseNetworkSubnetsInner.md) | Array subnets allocated in this Network |  |



