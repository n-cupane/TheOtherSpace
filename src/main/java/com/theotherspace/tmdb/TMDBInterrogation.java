package com.theotherspace.tmdb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.theotherspace.model.Movie;

public class TMDBInterrogation {
	
	private static final String AUTH_KEY="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMWRmMjE0YTRlZjE2MzIxYjEwODM5NWE2OWQzNGI1MSIsInN1YiI6IjY1ZmIyOTY0Y2Y2MmNkMDE2MzU1YTVlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1ir5j-dGpW6BhqUJtRTubF0ydn9R2IPbVE6huqCdTqk";

	public static List<Movie> searchMovies(String movieTitle) {
		
		try {
			
			List<Movie> moviesFound = new ArrayList<>();
			
			String formattedMovieTitle = movieTitle.trim().replace(" ", "%20");
			URL url = new URL("https://api.themoviedb.org/3/search/movie?query=" + formattedMovieTitle + "&include_adult=true&language=it-IT&page=1");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + AUTH_KEY);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//////////////////////////////
//			System.out.println("RISPOSTA HTTP:");
//			System.out.println(response.toString());
			
			JSONObject jsonResponse = new JSONObject(response.toString());
			JSONArray results = jsonResponse.getJSONArray("results");
			
			for (int i = 0; i < results.length(); i++) {
				
				JSONObject result = results.getJSONObject(i);
				
				long movieId = result.getInt("id");
				String title = result.getString("title");
				String posterPath = "";
				try {
					posterPath = result.getString("poster_path");
				} catch (Exception e) {
					System.out.println("ERROR FOR MOVIE " + title + ": " + e.getMessage());
				}
				
				Movie movieToAdd = new Movie();
				movieToAdd.setId(movieId);
				movieToAdd.setTitle(title);
				movieToAdd.setImageUrl("https://image.tmdb.org/t/p/w600_and_h900_bestv2/" + posterPath);
				
				moviesFound.add(movieToAdd);
			}
			
			connection.disconnect();
			
			return moviesFound;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Movie searchMovieById(long id) {
		
		try {
			
			Movie movie = new Movie();
			URL url = new URL("https://api.themoviedb.org/3/movie/" + id + "?language=it-IT");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + AUTH_KEY);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			JSONObject result = new JSONObject(response.toString());
			
			if (result != null) {
				
				String title = "";
				String posterPath = "";
				String genre = "";
				String description = "";
				int duration = 0;
				boolean forAdults = false;
				
				try {
					title = result.getString("title");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					posterPath = result.getString("poster_path");
				} catch (Exception e) {
					System.out.println("ERROR FOR MOVIE " + title + ": " + e.getMessage());
				}
				
				try {
					genre = result.getJSONArray("genres").getJSONObject(0).getString("name");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					forAdults = result.getBoolean("adult");
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				try {
					description = result.getString("overview");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {	
					duration = result.getInt("runtime");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				movie.setTitle(title);
				movie.setImageUrl("https://image.tmdb.org/t/p/w600_and_h900_bestv2/" + posterPath);
				movie.setGenreFromName(genre);
				movie.setOver18(forAdults);
				movie.setDescription(description);
				movie.setDuration(duration);
				
				return movie;
				
			}
			
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
