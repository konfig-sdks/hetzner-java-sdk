

# ServersCreateServerActionRequestPublicNet

Public Network options

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**enableIpv4** | **Boolean** | Attach an IPv4 on the public NIC. If false, no IPv4 address will be attached. |  [optional] |
|**enableIpv6** | **Boolean** | Attach an IPv6 on the public NIC. If false, no IPv6 address will be attached. |  [optional] |
|**ipv4** | **Integer** | ID of the ipv4 Primary IP to use. If omitted and enable_ipv4 is true, a new ipv4 Primary IP will automatically be created. |  [optional] |
|**ipv6** | **Integer** | ID of the ipv6 Primary IP to use. If omitted and enable_ipv6 is true, a new ipv6 Primary IP will automatically be created. |  [optional] |



