package com.example.ramapi_4;
import com.example.ramapi_4.AppObject.CartoonPers;
import com.example.ramapi_4.AppObject.Downloader;
import com.example.ramapi_4.AppObject.Parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import java.util.Arrays;

public class ParserTest {
    JSONObject jsonObject_character1;
    JSONObject jsonObject_character2;

    JSONObject jsonObject_list1;
    JSONObject jsonObject_list2;

    @Before
    public void setUP() throws JSONException {
        jsonObject_character1 = new JSONObject("{\"id\":65,\"name\":\"Chris\",\"status\":\"Alive\",\"species\":\"Humanoid\",\"type\":\"Microverse inhabitant\",\"gender\":\"Male\",\"origin\":{\"name\":\"Rick's Battery Microverse\",\"url\":\"https://rickandmortyapi.com/api/location/24\"},\"location\":{\"name\":\"Rick's Battery Microverse\",\"url\":\"https://rickandmortyapi.com/api/location/24\"},\"image\":\"https://rickandmortyapi.com/api/character/avatar/65.jpeg\",\"episode\":[\"https://rickandmortyapi.com/api/episode/17\"],\"url\":\"https://rickandmortyapi.com/api/character/65\",\"created\":\"2017-11-30T11:02:41.935Z\"}");
        jsonObject_character2 =  new JSONObject("{\"id\":36,\"name\":\"Beta-Seven\",\"status\":\"Alive\",\"species\":\"Alien\",\"type\":\"Hivemind\",\"gender\":\"unknown\",\"origin\":{\"name\":\"unknown\",\"url\":\"\"},\"location\":{\"name\":\"unknown\",\"url\":\"\"},\"image\":\"https://rickandmortyapi.com/api/character/avatar/36.jpeg\",\"episode\":[\"https://rickandmortyapi.com/api/episode/14\"],\"url\":\"https://rickandmortyapi.com/api/character/36\",\"created\":\"2017-11-05T09:31:08.952Z\"}");

        jsonObject_list1 = new JSONObject(Downloader.INSTANCE.getStringAbout("https://rickandmortyapi.com/api/episode"));
        jsonObject_list2 = new JSONObject(Downloader.INSTANCE.getStringAbout("https://rickandmortyapi.com/api/location"));
    }
    @Test
    public void parseStatusTest1(){
    String result = "Alive";
    Assert.assertTrue(result.contains(Parser.INSTANCE.parseStatus(jsonObject_character1)));
    }
    @Test
    public void parseStatusTest2(){
        String result = "Alive";
        Assert.assertTrue(result.contains(Parser.INSTANCE.parseStatus(jsonObject_character2)));
    }
    @Test
    public void parseLocationTest1(){
        String result = "Rick's Battery Microverse";
        Assert.assertTrue(result.contains(Parser.INSTANCE.parseLocation(jsonObject_character1)));
    }
    @Test
    public void parseLocationTest2(){
        String result = "unknown";
        Assert.assertTrue(result.contains(Parser.INSTANCE.parseLocation(jsonObject_character2)));
    }
    @Test
    public void parseSpeciesTest1(){
        String result = "Humanoid";
        Assert.assertTrue(result.contains(Parser.INSTANCE.parseSpecies(jsonObject_character1)));
    }
    @Test
    public void parseSpeciesTest2(){
        String result = "Alien";
        Assert.assertTrue(result.contains(Parser.INSTANCE.parseSpecies(jsonObject_character2)));
    }
    @Test
    public void parseEpisodesIdsTest1(){
        int[] result = {17};
        Assert.assertTrue(Arrays.equals(result,Parser.INSTANCE.parseEpisodesIds(jsonObject_character1)));
    }
    @Test
    public void parseEpisodesIdsTest2(){
        int[] result = {14};
        Assert.assertTrue(Arrays.equals(result,Parser.INSTANCE.parseEpisodesIds(jsonObject_character2)));
    }
    @Test
    public void getCountOfPagesTest1(){
        int result = 3;
        Assert.assertEquals("",result,Parser.INSTANCE.getCountOfPages(jsonObject_list1));
    }
    @Test
    public void getCountOfPagesTest2(){
        int result = 7;
        Assert.assertEquals("",result,Parser.INSTANCE.getCountOfPages(jsonObject_list2));
    }

}
