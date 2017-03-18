package com.example.android.popularmovies;

class TrailersInfo {

    private String trailerTitle;
    private String trailerUrl;


    TrailersInfo(String trailerTitle, String trailerUrl){
        this.trailerTitle = trailerTitle;
        this.trailerUrl = trailerUrl;
    }

    String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    String getTrailerTitle() {
        return trailerTitle;
    }

    public void setTrailerTitle(String trailerTitle) {
        this.trailerTitle = trailerTitle;
    }
}
