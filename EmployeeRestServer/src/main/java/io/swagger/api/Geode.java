/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.api;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author vladip
 */

@Component
public class Geode {
  private final ClientCache cache;
  private final Region<String, String> region;
  
  public Geode() {
    cache = new ClientCacheFactory()
        .addPoolLocator("localhost", 10334)
        .create();
    
    Region<String, String> r = cache.getRegion("employees");
    if (r == null)
        r = cache
        .<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
        .create("employees");
    
    region = r;
  }
  
  public Region<String, String> getRegion() {
    return region;
  }
}   

