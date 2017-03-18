package com.example.android.popularmovies;

class MovieReview {

    private String author;
    String content;

    MovieReview(String author, String content){
        this.author = author;
        this.content = content;
    }

    String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
