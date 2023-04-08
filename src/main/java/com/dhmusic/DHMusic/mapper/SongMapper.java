package com.dhmusic.DHMusic.mapper;

import com.dhmusic.DHMusic.entities.account.entities.Artist;
import com.dhmusic.DHMusic.entities.content.entities.Album;
import com.dhmusic.DHMusic.entities.content.entities.Song;
import com.dhmusic.DHMusic.entities.content.entities.SongDTO;
import com.dhmusic.DHMusic.repositories.account_repositories.ArtistRepository;
import com.dhmusic.DHMusic.repositories.content.repositories.AlbumRepository;
import com.dhmusic.DHMusic.repositories.content.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SongMapper {
    @Autowired
     ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public SongMapper(ArtistRepository artistRepository,
                      SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }


   /* public Song toSong(SongDTO songDTO){
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setGenre(songDTO.getGenre());
        song.setLength(songDTO.getLength());
        song.setIsPublic(songDTO.isPublic());
        Artist artist = artistRepository.findById(songDTO.getIdArtistOfSong());//Optional yes or not?
        song.setArtistOfSong(artist);
        Album album = albumRepository.findById(songDTO.getIdAlbumOfSong());
        song.setAlbumOfSong(album);
        return  songRepository.save(song);

    }

    */

    /*
    Sono stati usati i puntini perché la funzione non è stata definita completamente.
    È possibile che ci siano altri parametri da specificare per la funzione,
    come ad esempio il titolo della canzone o l'artista da assegnare.
    La sintassi completa potrebbe essere qualcosa del genere:
     song::setArtistOfSong(nomeArtista, nomeCanzone)
    dove "nomeArtista" e "nomeCanzone" sono i parametri da passare alla funzione
    per specificare l'artista e il titolo della canzone da aggiornare.
     */
   public Song toSong(SongDTO songDTO){
       Song song = new Song();
       song.setTitle(songDTO.getTitle());
       song.setGenre(songDTO.getGenre());
       song.setLength(songDTO.getLength());
       song.setIsPublic(songDTO.isPublic());
       Optional<Artist> artist = artistRepository.findById(songDTO.getIdArtistOfSong());
       artist.ifPresent(song::setArtistOfSong);
       Optional<Album> album = albumRepository.findById(songDTO.getIdAlbumOfSong());
       album.ifPresent(song::setAlbumOfSong);
       songRepository.save(song);
       return song;
   }





}
