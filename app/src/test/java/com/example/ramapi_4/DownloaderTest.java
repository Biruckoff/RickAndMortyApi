package com.example.ramapi_4;

import com.example.ramapi_4.AppObject.Downloader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class DownloaderTest {
    String BASEURL1;
    String BASEURL2;
    String BASEURL3;
    String BASEURL4;
    int id1;
    int id2;
    @Before
    public void setUp(){
    BASEURL1 = "https://rickandmortyapi.com/api/character/267";
    BASEURL2 = "https://rickandmortyapi.com/api/location/2";
    BASEURL3 = "https://rickandmortyapi.com/api/location/";
    BASEURL4 = "https://rickandmortyapi.com/api/character/";
    id1 = 2;
    id2 = 8;
    }
    @Test
    public void testGetStringAbout1(){
        String result = "{\"id\":267,\"name\":\"Plumber Rick\",\"status\":\"Alive\",\"species\":\"Human\",\"type\":\"\",\"gender\":\"Male\",\"origin\":{\"name\":\"unknown\",\"url\":\"\"},\"location\":{\"name\":\"Citadel of Ricks\",\"url\":\"https://rickandmortyapi.com/api/location/3\"},\"image\":\"https://rickandmortyapi.com/api/character/avatar/267.jpeg\",\"episode\":[\"https://rickandmortyapi.com/api/episode/28\"],\"url\":\"https://rickandmortyapi.com/api/character/267\",\"created\":\"2017-12-31T13:50:57.337Z\"} \n";
        Assert.assertTrue(result.contains(Downloader.INSTANCE.getStringAbout(BASEURL1)));
    }
    @Test
    public void testGetStringAbout2(){
        String result = "{\"id\":2,\"name\":\"Abadango\",\"type\":\"Cluster\",\"dimension\":\"unknown\",\"residents\":[\"https://rickandmortyapi.com/api/character/6\"],\"url\":\"https://rickandmortyapi.com/api/location/2\",\"created\":\"2017-11-10T13:06:38.182Z\"}";
        Assert.assertTrue(result.contains(Downloader.INSTANCE.getStringAbout(BASEURL2)));
    }

    @Test
    public void testGetStringAbout3(){
        String result = "{\"id\":2,\"name\":\"Abadango\",\"type\":\"Cluster\",\"dimension\":\"unknown\",\"residents\":[\"https://rickandmortyapi.com/api/character/6\"],\"url\":\"https://rickandmortyapi.com/api/location/2\",\"created\":\"2017-11-10T13:06:38.182Z\"}";
        Assert.assertTrue(result.contains(Downloader.INSTANCE.getStringAbout(id1,BASEURL3)));
    }
    @Test
    public void testGetStringAbout4(){
        String result = "{\"id\":8,\"name\":\"Adjudicator Rick\",\"status\":\"Dead\",\"species\":\"Human\",\"type\":\"\",\"gender\":\"Male\",\"origin\":{\"name\":\"unknown\",\"url\":\"\"},\"location\":{\"name\":\"Citadel of Ricks\",\"url\":\"https://rickandmortyapi.com/api/location/3\"},\"image\":\"https://rickandmortyapi.com/api/character/avatar/8.jpeg\",\"episode\":[\"https://rickandmortyapi.com/api/episode/28\"],\"url\":\"https://rickandmortyapi.com/api/character/8\",\"created\":\"2017-11-04T20:03:34.737Z\"}";
        Assert.assertTrue(result.contains(Downloader.INSTANCE.getStringAbout(id2,BASEURL4)));
    }


}
