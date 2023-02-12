package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IpserviceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.220.52.230");
        assertEquals(ipLocation , "<GeoIP><Country>RU</Country><State>88</State></GeoIP>");
    }
    @Test
    public void testInvalidIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.220.52.xxx");
        assertEquals(ipLocation , "<GeoIP><Country>RU</Country><State>88</State></GeoIP>");
    }

}
