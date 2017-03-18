package com.example.android.popularmovies;

import java.io.Serializable;

@SuppressWarnings("serial")
class MovieInfo implements Serializable {


    private String imageUrl;
    private String title;
    private String plot;
    private String rating;
    private String releaseDate;
    String id;


    MovieInfo(String imageUrl, String title, String plot, String rating, String releaseDate, String id){

        this.imageUrl = imageUrl;
        this.title = title;
        this.plot = plot;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.id = id;
    }
    String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}
