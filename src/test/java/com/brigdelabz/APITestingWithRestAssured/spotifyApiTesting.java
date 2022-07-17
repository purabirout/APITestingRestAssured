package com.brigdelabz.APITestingWithRestAssured;


	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.http.Method;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	public class spotifyApiTesting {
	        RequestSpecification requestSpecification;
	        String token = "Bearer BQDyAw8VoNRdeAtsuaTBoRzwjFE9YqpGSXBveAnPVE-"
	        		+ "LfvhTCyun6Qxyvq7cT5unyP1cWJeimp-oU-"
	        		+ "rwsd6LBu49fXWzfZx1XwMo2vIrO3-8SX2ecZ7JlztTydWvOlHPItpskwVECfyirMV7WVokkn1foQiYCYs_"
	        		+ "4JIv3CyxXMYhCEdHBPlv1cSHJKjvMLb0BxGhLP625PUNzFSQvTau_eHFR5katXF7_"
	        		+ "tHDe-haICdIKNmD1-mcTMgjlfGcelQiXGv5Bf_"
	        		+ "kg9z3FaruNjh__NVAm7TqwVNLkb-jJJ1r";
	        String user_ID = "31ndvyvcb33ktfpqrmywaoevaeua";
	        String ID;


	    @Test
	    public void getUserProfileTest() {
	        requestSpecification = RestAssured.given();
	        requestSpecification.contentType(ContentType.JSON);
	        requestSpecification.header("Authorization",token);
	        Response response = requestSpecification.request(Method.GET,"https://api.spotify.com/v1/users/31ndvyvcb33ktfpqrmywaoevaeua");
	        response.prettyPrint();
	        user_ID = response.path("id");
	        System.out.println("id:"+user_ID);
	        int statusCode = response.getStatusCode();
	        Assert.assertEquals(statusCode,200);

	    }

	    @Test
	    public void createPlaylistTest() {
	        requestSpecification = RestAssured.given();
	        requestSpecification.contentType(ContentType.JSON);
	        requestSpecification.header("Authorization",token);
	        requestSpecification.body("{\n" +
	                "  \"name\": \"SundayFunday\",\n" +
	                "  \"description\": \"Holidays description\",\n" +
	                "  \"public\": false\n" +
	                "}");
	        Response response = requestSpecification.request(Method.POST,"https://api.spotify.com/v1/users/"+user_ID+"/playlists");
	        response.prettyPrint();
	        int statusCode = response.getStatusCode();
	        Assert.assertEquals(statusCode, 201);
	    }

	    @Test
	    public void Search_Item() {
	        requestSpecification = RestAssured.given();
	        requestSpecification.contentType(ContentType.JSON);
	        requestSpecification.header("Authorization",token);
	        requestSpecification.queryParam("q","Teri baaton");
	        requestSpecification.queryParam("type","track");
	        requestSpecification.queryParam("limit","1");

	        Response response  = requestSpecification.request(Method.GET,"https://api.spotify.com/v1/search");
	        response.prettyPrint();

	        int statusCode = response.getStatusCode();
	        Assert.assertEquals(statusCode,200);
	    }

	    @Test
	    public void addSongsTest(){
	        String PlayList_ID = "1OPcbEQFpYx3XPOW0kXbZ2";
	         requestSpecification = RestAssured.given();
	            requestSpecification.contentType(ContentType.JSON);
	            requestSpecification.header("Authorization",token);
	            requestSpecification.body("{\n" +
	                    "  \"uris\": [\n" +
	                    "    \"spotify:track:5juIfRjWCX15RvLD6AKqWr\"\n" +
	                    "  ],\n" +
	                    "  \"position\": 0\n" +
	                    "}");
	            Response response = requestSpecification.request(Method.POST,"https://api.spotify.com/v1/playlists/"+PlayList_ID+"/tracks");
	            response.prettyPrint();
	            int statusCode = response.getStatusCode();
	            Assert.assertEquals(statusCode, 201);
	        }

	    @Test
	    public void deleteSong(){
	        String PlayList_ID = "1OPcbEQFpYx3XPOW0kXbZ2";
	        requestSpecification = RestAssured.given();
	        requestSpecification.contentType(ContentType.JSON);
	        requestSpecification.header("Authorization",token);
	        requestSpecification.body("{ \"tracks\": [{ \"uri\": \"spotify:track:5juIfRjWCX15RvLD6AKqWr\"} ] }");
	        Response response = requestSpecification.request(Method.DELETE,"\thttps://api.spotify.com/v1/playlists/"+PlayList_ID+"/tracks");
	        response.prettyPrint();
	        int statusCode = response.getStatusCode();
	        Assert.assertEquals(statusCode, 200);

	    }

	    }
