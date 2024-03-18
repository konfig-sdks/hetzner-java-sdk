

# ServersUpdateServerResponseServerPublicNet

Public network information. The Server's IPv4 address can be found in `public_net->ipv4->ip`

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**firewalls** | [**List&lt;ServerPublicNetFirewall3&gt;**](ServerPublicNetFirewall3.md) | Firewalls applied to the public network interface of this Server |  [optional] |
|**floatingIps** | **List&lt;Long&gt;** | IDs of Floating IPs assigned to this Server |  |
|**ipv4** | [**ServersUpdateServerResponseServerPublicNetIpv4**](ServersUpdateServerResponseServerPublicNetIpv4.md) |  |  |
|**ipv6** | [**ServersUpdateServerResponseServerPublicNetIpv6**](ServersUpdateServerResponseServerPublicNetIpv6.md) |  |  |



