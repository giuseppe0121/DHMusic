package com.dhmusic.DHMusic.entities.content.entities;


import com.dhmusic.DHMusic.entities.account.entities.Artist;

public class SongDTO {

   private Long id;
   private String title;
    private float length;
   private String genre;
    private Long idArtistOfSong;
    private Long idAlbumOfSong;
    private boolean isPublic;

    public SongDTO(){
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getIdArtistOfSong() {
        return idArtistOfSong;
    }

    public void setIdArtistOfSong(Long idArtistOfSong) {
        this.idArtistOfSong = idArtistOfSong;
    }

    public Album getIdAlbumOfSong() {
        return idAlbumOfSong;
    }

    public void setIdAlbumOfSong(Long idAlbumOfSong) {
        this.idAlbumOfSong = idAlbumOfSong;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isAPublic) {
        isPublic = isAPublic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
