package com.konfigthis.client;

import com.konfigthis.client.api.ActionsApi;
import com.konfigthis.client.api.CertificateActionsApi;
import com.konfigthis.client.api.CertificatesApi;
import com.konfigthis.client.api.DatacentersApi;
import com.konfigthis.client.api.FirewallActionsApi;
import com.konfigthis.client.api.FirewallsApi;
import com.konfigthis.client.api.FloatingIpActionsApi;
import com.konfigthis.client.api.FloatingIpsApi;
import com.konfigthis.client.api.IsosApi;
import com.konfigthis.client.api.ImageActionsApi;
import com.konfigthis.client.api.ImagesApi;
import com.konfigthis.client.api.LoadBalancerActionsApi;
import com.konfigthis.client.api.LoadBalancerTypesApi;
import com.konfigthis.client.api.LoadBalancersApi;
import com.konfigthis.client.api.LocationsApi;
import com.konfigthis.client.api.NetworkActionsApi;
import com.konfigthis.client.api.NetworksApi;
import com.konfigthis.client.api.PlacementGroupsApi;
import com.konfigthis.client.api.PricingApi;
import com.konfigthis.client.api.PrimaryIpActionsApi;
import com.konfigthis.client.api.PrimaryIpsApi;
import com.konfigthis.client.api.SshKeysApi;
import com.konfigthis.client.api.ServerActionsApi;
import com.konfigthis.client.api.ServerTypesApi;
import com.konfigthis.client.api.ServersApi;
import com.konfigthis.client.api.VolumeActionsApi;
import com.konfigthis.client.api.VolumesApi;

public class Hetzner {
    private ApiClient apiClient;
    public final ActionsApi actions;
    public final CertificateActionsApi certificateActions;
    public final CertificatesApi certificates;
    public final DatacentersApi datacenters;
    public final FirewallActionsApi firewallActions;
    public final FirewallsApi firewalls;
    public final FloatingIpActionsApi floatingIpActions;
    public final FloatingIpsApi floatingIps;
    public final IsosApi isos;
    public final ImageActionsApi imageActions;
    public final ImagesApi images;
    public final LoadBalancerActionsApi loadBalancerActions;
    public final LoadBalancerTypesApi loadBalancerTypes;
    public final LoadBalancersApi loadBalancers;
    public final LocationsApi locations;
    public final NetworkActionsApi networkActions;
    public final NetworksApi networks;
    public final PlacementGroupsApi placementGroups;
    public final PricingApi pricing;
    public final PrimaryIpActionsApi primaryIpActions;
    public final PrimaryIpsApi primaryIps;
    public final SshKeysApi sshKeys;
    public final ServerActionsApi serverActions;
    public final ServerTypesApi serverTypes;
    public final ServersApi servers;
    public final VolumeActionsApi volumeActions;
    public final VolumesApi volumes;

    public Hetzner() {
        this(null);
    }

    public Hetzner(Configuration configuration) {
        this.apiClient = new ApiClient(null, configuration);
        this.actions = new ActionsApi(this.apiClient);
        this.certificateActions = new CertificateActionsApi(this.apiClient);
        this.certificates = new CertificatesApi(this.apiClient);
        this.datacenters = new DatacentersApi(this.apiClient);
        this.firewallActions = new FirewallActionsApi(this.apiClient);
        this.firewalls = new FirewallsApi(this.apiClient);
        this.floatingIpActions = new FloatingIpActionsApi(this.apiClient);
        this.floatingIps = new FloatingIpsApi(this.apiClient);
        this.isos = new IsosApi(this.apiClient);
        this.imageActions = new ImageActionsApi(this.apiClient);
        this.images = new ImagesApi(this.apiClient);
        this.loadBalancerActions = new LoadBalancerActionsApi(this.apiClient);
        this.loadBalancerTypes = new LoadBalancerTypesApi(this.apiClient);
        this.loadBalancers = new LoadBalancersApi(this.apiClient);
        this.locations = new LocationsApi(this.apiClient);
        this.networkActions = new NetworkActionsApi(this.apiClient);
        this.networks = new NetworksApi(this.apiClient);
        this.placementGroups = new PlacementGroupsApi(this.apiClient);
        this.pricing = new PricingApi(this.apiClient);
        this.primaryIpActions = new PrimaryIpActionsApi(this.apiClient);
        this.primaryIps = new PrimaryIpsApi(this.apiClient);
        this.sshKeys = new SshKeysApi(this.apiClient);
        this.serverActions = new ServerActionsApi(this.apiClient);
        this.serverTypes = new ServerTypesApi(this.apiClient);
        this.servers = new ServersApi(this.apiClient);
        this.volumeActions = new VolumeActionsApi(this.apiClient);
        this.volumes = new VolumesApi(this.apiClient);
    }

}
