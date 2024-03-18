

# ServersGetAllResponseServersInnerPublicNet

Public network information. The Server's IPv4 address can be found in `public_net->ipv4->ip`

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**firewalls** | [**List&lt;ServerPublicNetFirewall&gt;**](ServerPublicNetFirewall.md) | Firewalls applied to the public network interface of this Server |  [optional] |
|**floatingIps** | **List&lt;Long&gt;** | IDs of Floating IPs assigned to this Server |  |
|**ipv4** | [**ServersGetAllResponseServersInnerPublicNetIpv4**](ServersGetAllResponseServersInnerPublicNetIpv4.md) |  |  |
|**ipv6** | [**ServersGetAllResponseServersInnerPublicNetIpv6**](ServersGetAllResponseServersInnerPublicNetIpv6.md) |  |  |



