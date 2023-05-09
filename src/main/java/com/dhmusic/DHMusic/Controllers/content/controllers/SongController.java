package com.dhmusic.DHMusic.Controllers.content.controllers;

import com.dhmusic.DHMusic.entities.content.entities.Song;
import com.dhmusic.DHMusic.entities.content.entities.SongDTO;
import com.dhmusic.DHMusic.repositories.content.repositories.SongRepository;
import com.dhmusic.DHMusic.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService; // per accesso al database
    @Autowired
    private SongRepository songRepository;



    //------------------------------------------------------------------------------------------------------

    /**
     * mostra la lista di canzoni
     * @return tutte le canzoni presenti all'interno della repository
     */
    @GetMapping
    public List<Song> getSongs() {           //Funziona
        return songRepository.findAll();

    }

    //------------------------------------------------------------------------------------------------------

    /**
     * mostra la singola canzone per titolo
     * @param title come @PathVariable
     * @return canzone trovata all'interno della repository con lo stesso titolo
     */
    @GetMapping("/get/title/{title}")
    public Song getSongTitle(@PathVariable String title) {   //Funziona
        return songRepository.findSongByTitle(title);
    }

    //------------------------------------------------------------------------------------------------------

    /**
     * Elimina una canzone all'interno del database.
     * @param id come @PathVariable.
     * @return salvataggio dell'eliminazione avvenuta.
     * @throws Exception Ok se la canzone è stata eliminata con successo.
     */
    @DeleteMapping("/delete/{id}")                                    //Funziona
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) throws Exception {
        songService.deleteSong(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //------------------------------------------------------------------------------------------------------
    /**
     * Elimina tutte le canzoni presenti all'interno del database
     */
    @DeleteMapping("/delete/all")            //funziona
    public void deleteAll() {
        songService.deleteAll();
    }


    //------------------------------------------------------------------------------------------------------

    /**
     * Aggiunge una nuova canzone
     * @param songDTO come @RequestBody
     * @return lo stato "Created" con messaggio che la canzone è stata creata con successo.
     * @throws Exception Bad Request con messaggio dell'eventuale errore presente.
     */

    @PostMapping("/create")                                     //funziona
    public ResponseEntity<?> createSong(@RequestBody SongDTO songDTO) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(songDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/add-file-to-song")
    public ResponseEntity addFileToSong(@RequestParam long songId, @RequestParam MultipartFile file) {
        try {
            return ResponseEntity.ok(songService.addFileToSong(songId, file));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/create-with-file", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity createSongWithFile(@RequestPart("song") SongDTO songDTO, @RequestPart("file") MultipartFile file) {
        try {
            Song song = songService.addSong(songDTO);
            return ResponseEntity.ok(songService.addFileToSong(song.getId(), file));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------
    //Aggiorna un Song nel database DTO

    /**
     * Aggiorna una canzone presente all'interno del database
     * @param id come @PathVariable
     * @param song come @RequestBody
     * @return se l'aggiornamento è stato accettato o no tramite una Bad request.
     */
    @PutMapping("/update/{id}")                               //funziona
    public ResponseEntity<?> updateSong(@PathVariable Long id, @RequestBody SongDTO song) {
        try {
            songService.updateSong(id, song);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    //------------------------------------------------------------------------------------------------------

    /**
     * Mostra la singola canzone richiesta
     * @param id come @PathVariable
     * @return i dati della canzone richiesta o un "Not found" nel caso in cui la canzone non viene trovata
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id){
        try {
            songService.getSongById(id);
            Song existSong = songRepository.findSongById(id);
            return ResponseEntity.ok(existSong);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}