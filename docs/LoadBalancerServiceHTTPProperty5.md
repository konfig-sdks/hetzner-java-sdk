

# LoadBalancerServiceHTTPProperty5

Configuration option for protocols http and https

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**certificates** | **List&lt;Long&gt;** | IDs of the Certificates to use for TLS/SSL termination by the Load Balancer; empty for TLS/SSL passthrough or if &#x60;protocol&#x60; is &#x60;http&#x60;. |  [optional] |
|**cookieLifetime** | **Integer** | Lifetime of the cookie used for sticky sessions (in seconds). |  [optional] |
|**cookieName** | **String** | Name of the cookie used for sticky sessions. |  [optional] |
|**redirectHttp** | **Boolean** | Redirect HTTP requests to HTTPS. Only available if &#x60;protocol&#x60; is &#x60;https&#x60;. |  [optional] |
|**stickySessions** | **Boolean** | Use sticky sessions. Only available if &#x60;protocol&#x60; is &#x60;http&#x60; or &#x60;https&#x60;. |  [optional] |



